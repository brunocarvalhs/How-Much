package br.com.brunocarvalhs.howmuch.app.foundation.sdks

import android.content.Context
import androidx.startup.Initializer
import br.com.brunocarvalhs.howmuch.BuildConfig
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.perf.metrics.AddTrace

class FirebaseInitializer : Initializer<Unit> {

    @AddTrace(name = "SDK-Firebase")
    override fun create(context: Context) {
        FirebaseApp.initializeApp(context)

    }

    @AddTrace(name = "SDK-Firebase-Analytics")
    private fun initializeAnalytics(context: Context) {
        FirebaseAnalytics.getInstance(context)
    }

    @AddTrace(name = "SDK-Firebase-Crashlytics")
    private fun initializeCrashlytics() {
        FirebaseCrashlytics.getInstance().isCrashlyticsCollectionEnabled = BuildConfig.DEBUG.not()
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}