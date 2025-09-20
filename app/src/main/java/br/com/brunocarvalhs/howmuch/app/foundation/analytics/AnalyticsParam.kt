package br.com.brunocarvalhs.howmuch.app.foundation.analytics

import com.google.firebase.analytics.FirebaseAnalytics

enum class AnalyticsParam(val paramName: String) {
    SCREEN_NAME(FirebaseAnalytics.Param.SCREEN_NAME),
    SCREEN_CLASS(FirebaseAnalytics.Param.SCREEN_CLASS),
    ITEM_ID(FirebaseAnalytics.Param.ITEM_ID),
    ITEM_NAME(FirebaseAnalytics.Param.ITEM_NAME),
    ERROR_MESSAGE("error_message"),
    TIMESTAMP("timestamp"),
    DEVICE("device"),
    OS_VERSION("os_version"),
    APP_VERSION("app_version"),
    DEBUG("debug"),
    LIFECYCLE("lifecycle"),
    SHOPPING_CART_ID("shopping_cart_id")
}
