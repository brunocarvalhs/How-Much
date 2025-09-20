package br.com.brunocarvalhs.howmuch.app.foundation.analytics

import com.google.firebase.analytics.FirebaseAnalytics

enum class AnalyticsParam(val paramName: String) {
    SCREEN_NAME(FirebaseAnalytics.Param.SCREEN_NAME),
    SCREEN_CLASS(FirebaseAnalytics.Param.SCREEN_CLASS),
    ITEM_ID(FirebaseAnalytics.Param.ITEM_ID),
    ITEM_NAME(FirebaseAnalytics.Param.ITEM_NAME),
    ITEM_CATEGORY(FirebaseAnalytics.Param.ITEM_CATEGORY),
    SEARCH_TERM(FirebaseAnalytics.Param.SEARCH_TERM),
    TRANSACTION_ID(FirebaseAnalytics.Param.TRANSACTION_ID),
    PRICE(FirebaseAnalytics.Param.PRICE),
    CURRENCY(FirebaseAnalytics.Param.CURRENCY),
    VALUE(FirebaseAnalytics.Param.VALUE),
    CONTENT_TYPE(FirebaseAnalytics.Param.CONTENT_TYPE),
    ERROR_MESSAGE("error_message"),

    // Dados de contexto
    TIMESTAMP("timestamp"),
    DEVICE("device"),
    OS_VERSION("os_version"),
    APP_VERSION("app_version"),
    DEBUG("debug"),
    CONNECTION_TYPE("connection_type"),
    USER_ID("user_id"),

    // Novos parâmetros adicionados
    SCREEN_ORIENTATION("screen_orientation"),
    BATTERY_LEVEL("battery_level"),
    NETWORK_TYPE("network_type"),
    LANGUAGE("language"),
    REGION("region"),
    SESSION_DURATION("session_duration"),
    BUTTON_TYPE("button_type"),
    FEATURE_USED("feature_used"),
    NOTIFICATION_TYPE("notification_type"),
    PERMISSION_TYPE("permission_type"),
    FEEDBACK_TYPE("feedback_type"),

    // Product
    SHOPPING_CART_ID("shopping_cart_id")
}
