package br.com.brunocarvalhs.howmuch.app.foundation.analytics

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.navigation.NavController

// Lifecycle -------------------------------------------------------------------------------------------------------------
fun Context.openApp() {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.APP_OPEN,
        mapOf(
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis(),
            AnalyticsParam.APP_VERSION to packageManager.getPackageInfo(
                packageName,
                0
            ).versionName.orEmpty(),
            AnalyticsParam.DEVICE to Build.MODEL,
            AnalyticsParam.OS_VERSION to Build.VERSION.RELEASE
        )
    )
}

fun Context.appBackground() {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.APP_BACKGROUND,
        mapOf(
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.appForeground() {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.APP_FOREGROUND,
        mapOf(
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Activity.firstOpen() {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.FIRST_OPEN,
        mapOf(
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis(),
            AnalyticsParam.APP_VERSION to packageManager.getPackageInfo(
                packageName,
                0
            ).versionName.orEmpty(),
            AnalyticsParam.DEVICE to Build.MODEL,
            AnalyticsParam.OS_VERSION to Build.VERSION.RELEASE
        )
    )
}

fun NavController.trackNavigation() {
    addOnDestinationChangedListener { _, destination, _ ->
        AnalyticsEvents.trackScreen(destination.route ?: "Unknown")
    }
}

internal fun trackClick(
    viewId: String,
    viewName: String? = null,
    screenName: String? = null
) = {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.CLICK,
        mapOf(
            AnalyticsParam.ITEM_ID to viewId,
            AnalyticsParam.ITEM_NAME to (viewName ?: viewId),
            AnalyticsParam.SCREEN_NAME to (screenName ?: "Unknown"),
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Throwable.trackError(message: String, screenName: String? = null) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.ERROR,
        mapOf(
            AnalyticsParam.ERROR_MESSAGE to message,
            AnalyticsParam.SCREEN_NAME to (screenName ?: "Unknown"),
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Throwable.trackCrash(reason: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.CRASH,
        mapOf(
            AnalyticsParam.ERROR_MESSAGE to reason,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Throwable.trackEmptyState(screenName: String, reason: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.EMPTY_STATE,
        mapOf(
            AnalyticsParam.SCREEN_NAME to screenName,
            AnalyticsParam.ERROR_MESSAGE to reason,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}
