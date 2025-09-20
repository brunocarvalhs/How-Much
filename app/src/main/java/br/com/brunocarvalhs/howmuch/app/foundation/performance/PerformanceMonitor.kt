package br.com.brunocarvalhs.howmuch.app.foundation.performance

import android.app.Activity
import android.app.Application
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.core.app.FrameMetricsAggregator
import androidx.metrics.performance.JankStats
import br.com.brunocarvalhs.howmuch.BuildConfig
import com.google.firebase.perf.FirebasePerformance
import com.google.firebase.perf.metrics.Trace
import java.util.concurrent.ConcurrentLinkedQueue
import androidx.core.util.size

object PerformanceMonitor : Application.ActivityLifecycleCallbacks {

    private val firebasePerformance = FirebasePerformance.getInstance()
    private val monitors = mutableMapOf<Activity, ActivityPerformance>()

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

    override fun onActivityStarted(activity: Activity) {
        val perf = ActivityPerformance(activity)
        monitors[activity] = perf
        perf.startMonitoring()
    }

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {
        monitors.remove(activity)?.stopMonitoring()
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityDestroyed(activity: Activity) {
        monitors.remove(activity)
    }

    private class ActivityPerformance(private val activity: Activity) {

        private var frameMetricsAggregator: FrameMetricsAggregator? = null
        private var jankStats: JankStats? = null
        private var trace: Trace? = null

        private val frameQueue = ConcurrentLinkedQueue<Double>()
        private val handler = Handler(Looper.getMainLooper())
        private val reportIntervalMs = 1000L
        private val jankThresholdMs = 16.0

        private val periodicReporter = object : Runnable {
            override fun run() {
                reportFrameSummary()
                handler.postDelayed(this, reportIntervalMs)
            }
        }

        fun startMonitoring() {
            startFirebaseTrace()
            startFrameMetricsAggregator()
            startJankStats()
            handler.postDelayed(periodicReporter, reportIntervalMs)
        }

        fun stopMonitoring() {
            stopJankStats()
            reportAndStopFrameMetrics()
            stopPeriodicReporting()
            stopFirebaseTrace()
        }

        private fun startFirebaseTrace() {
            trace = firebasePerformance.newTrace("activity_performance_${activity.localClassName}")
                .apply { start() }
        }

        private fun startFrameMetricsAggregator() {
            frameMetricsAggregator = FrameMetricsAggregator(
                FrameMetricsAggregator.TOTAL_DURATION or
                        FrameMetricsAggregator.DRAW_DURATION or
                        FrameMetricsAggregator.INPUT_DURATION or
                        FrameMetricsAggregator.SYNC_DURATION or
                        FrameMetricsAggregator.SWAP_DURATION or
                        FrameMetricsAggregator.ANIMATION_DURATION or
                        FrameMetricsAggregator.LAYOUT_MEASURE_DURATION
            ).apply { add(activity) }
        }

        private fun startJankStats() {
            jankStats = JankStats.createAndTrack(activity.window) { frameData ->
                val frameTimeMs = frameData.frameDurationUiNanos / 1_000_000.0
                frameQueue.add(frameTimeMs)
                if (frameTimeMs > jankThresholdMs && BuildConfig.DEBUG) {
                    Log.d("PerformanceMonitor", "JANK detected: $frameTimeMs ms")
                }
            }
        }

        private fun stopJankStats() {
            jankStats?.isTrackingEnabled = false
            jankStats = null
        }

        private fun stopPeriodicReporting() {
            handler.removeCallbacks(periodicReporter)
            frameQueue.clear()
        }

        private fun stopFirebaseTrace() {
            trace?.stop()
            trace = null
        }

        private fun reportAndStopFrameMetrics() {
            frameMetricsAggregator?.let { aggregator ->
                aggregator.metrics?.forEachIndexed { index, sparseArray ->
                    sparseArray?.let { array ->
                        val metricName = metricNameForIndex(index)
                        val metricsMap = arrayToReadableMap(array)
                        sendToFirebase(
                            "frame_metrics_${activity.localClassName}_$metricName",
                            metricsMap
                        )
                        if (BuildConfig.DEBUG) {
                            Log.d("PerformanceMonitor", "Metric: $metricName, Data: $metricsMap")
                        }
                    }
                }
                aggregator.remove(activity)
            }
            frameMetricsAggregator = null
        }

        private fun reportFrameSummary() {
            if (frameQueue.isEmpty()) return

            val frames = frameQueue.toList()
            frameQueue.clear()

            val average = frames.average()
            val max = frames.maxOrNull() ?: 0.0
            val p95 = frames.sorted().let { sorted ->
                sorted[(sorted.size * 0.95).toInt().coerceAtMost(sorted.size - 1)]
            }

            val metricsMap = mapOf(
                "average_frame_ms" to average,
                "max_frame_ms" to max,
                "p95_frame_ms" to p95,
                "total_frames" to frames.size
            )

            sendToFirebase("frame_summary_${activity.localClassName}", metricsMap)
        }

        private fun metricNameForIndex(index: Int): String = when (index) {
            0 -> "TOTAL_DURATION"
            1 -> "DRAW_DURATION"
            2 -> "INPUT_DURATION"
            3 -> "SYNC_DURATION"
            4 -> "SWAP_DURATION"
            5 -> "ANIMATION_DURATION"
            6 -> "LAYOUT_MEASURE_DURATION"
            else -> "UNKNOWN"
        }

        private fun arrayToReadableMap(array: android.util.SparseIntArray): Map<String, String> {
            val map = mutableMapOf<String, String>()
            for (i in 0 until array.size) {
                val timeMs = array.keyAt(i)
                val count = array.valueAt(i)
                map["${timeMs}ms"] = "$count frames"
            }
            return map
        }

        private fun sendToFirebase(eventName: String, params: Map<String, Any>) {
            trace?.apply {
                params.forEach { (key, value) ->
                    putAttribute(key, value.toString())
                }
            }
            if (BuildConfig.DEBUG) {
                Log.d("PerformanceMonitor", "Event: $eventName, Params: $params")
            }
        }
    }
}
