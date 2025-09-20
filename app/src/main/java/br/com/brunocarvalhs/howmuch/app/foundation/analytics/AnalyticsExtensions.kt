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

// Navegação -------------------------------------------------------------------------------------------------------------
fun NavController.trackNavigation() {
    addOnDestinationChangedListener { _, destination, _ ->
        AnalyticsEvents.trackScreen(destination.route ?: "Unknown")
    }
}

// UI ----------------------------------------------------------------------------------------------------------------------
fun Modifier.trackClick(
    viewId: String,
    viewName: String? = null,
    screenName: String? = null
): Modifier = pointerInput(Unit) {
    detectTapGestures(
        onTap = {
            AnalyticsEvents.trackEvent(
                AnalyticsEvent.CLICK,
                mapOf(
                    AnalyticsParam.ITEM_ID to viewId,
                    AnalyticsParam.ITEM_NAME to (viewName ?: viewId),
                    AnalyticsParam.SCREEN_NAME to (screenName ?: "Unknown"),
                    AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
                )
            )
        },
        onLongPress = {
            AnalyticsEvents.trackEvent(
                AnalyticsEvent.CLICK,
                mapOf(
                    AnalyticsParam.ITEM_ID to viewId,
                    AnalyticsParam.ITEM_NAME to (viewName ?: viewId),
                    AnalyticsParam.SCREEN_NAME to (screenName ?: "Unknown"),
                    AnalyticsParam.CONTENT_TYPE to "long_press",
                    AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
                )
            )
        }
    )
}

// Errors ------------------------------------------------------------------------------------------------------------------
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

fun Throwable.trackEmptyState(analytics: AnalyticsEvents, screenName: String, reason: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.EMPTY_STATE,
        mapOf(
            AnalyticsParam.SCREEN_NAME to screenName,
            AnalyticsParam.ERROR_MESSAGE to reason,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

// Novos eventos de analytics ------------------------------------------------------------------------------------------------
fun Context.trackLogout() {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.LOGOUT,
        mapOf(
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackSettingsChange(settingName: String, value: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.SETTINGS_CHANGE,
        mapOf(
            AnalyticsParam.FEATURE_USED to settingName,
            AnalyticsParam.VALUE to value,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackNotificationReceived(type: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.NOTIFICATION_RECEIVED,
        mapOf(
            AnalyticsParam.NOTIFICATION_TYPE to type,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackNotificationOpened(type: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.NOTIFICATION_OPENED,
        mapOf(
            AnalyticsParam.NOTIFICATION_TYPE to type,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackTutorialStarted(tutorialName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.TUTORIAL_STARTED,
        mapOf(
            AnalyticsParam.FEATURE_USED to tutorialName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackTutorialCompleted(tutorialName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.TUTORIAL_COMPLETED,
        mapOf(
            AnalyticsParam.FEATURE_USED to tutorialName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackFeedbackSubmitted(type: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.FEEDBACK_SUBMITTED,
        mapOf(
            AnalyticsParam.FEEDBACK_TYPE to type,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackPermissionRequested(permission: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.PERMISSION_REQUESTED,
        mapOf(
            AnalyticsParam.PERMISSION_TYPE to permission,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackPermissionGranted(permission: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.PERMISSION_GRANTED,
        mapOf(
            AnalyticsParam.PERMISSION_TYPE to permission,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackPermissionDenied(permission: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.PERMISSION_DENIED,
        mapOf(
            AnalyticsParam.PERMISSION_TYPE to permission,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackRefresh(screenName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.REFRESH,
        mapOf(
            AnalyticsParam.SCREEN_NAME to screenName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackFavoriteAdded(itemId: String, itemName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.FAVORITE_ADDED,
        mapOf(
            AnalyticsParam.ITEM_ID to itemId,
            AnalyticsParam.ITEM_NAME to itemName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackFavoriteRemoved(itemId: String, itemName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.FAVORITE_REMOVED,
        mapOf(
            AnalyticsParam.ITEM_ID to itemId,
            AnalyticsParam.ITEM_NAME to itemName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackShareSuccess(itemId: String, itemName: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.SHARE_SUCCESS,
        mapOf(
            AnalyticsParam.ITEM_ID to itemId,
            AnalyticsParam.ITEM_NAME to itemName,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}

fun Context.trackShareFailure(itemId: String, itemName: String, reason: String) {
    AnalyticsEvents.trackEvent(
        AnalyticsEvent.SHARE_FAILURE,
        mapOf(
            AnalyticsParam.ITEM_ID to itemId,
            AnalyticsParam.ITEM_NAME to itemName,
            AnalyticsParam.ERROR_MESSAGE to reason,
            AnalyticsParam.TIMESTAMP to System.currentTimeMillis()
        )
    )
}
