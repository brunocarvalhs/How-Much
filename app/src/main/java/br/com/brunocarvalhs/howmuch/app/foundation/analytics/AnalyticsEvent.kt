package br.com.brunocarvalhs.howmuch.app.foundation.analytics

import com.google.firebase.analytics.FirebaseAnalytics

enum class AnalyticsEvent(val eventName: String) {
    // Eventos de ciclo de vida
    APP_OPEN("app_open"),
    SESSION_START("session_start"),
    FIRST_OPEN("first_open"),
    APP_BACKGROUND("app_background"),
    APP_FOREGROUND("app_foreground"),

    // Eventos de navegação
    SCREEN_VIEW(FirebaseAnalytics.Event.SCREEN_VIEW),

    // Interações de UI
    CLICK("ui_click"),
    SEARCH(FirebaseAnalytics.Event.SEARCH),
    SHARE(FirebaseAnalytics.Event.SHARE),

    // Funil de negócio
    ADD_TO_CART(FirebaseAnalytics.Event.ADD_TO_CART),
    REMOVE_FROM_CART(FirebaseAnalytics.Event.REMOVE_FROM_CART),
    BEGIN_CHECKOUT(FirebaseAnalytics.Event.BEGIN_CHECKOUT),
    PURCHASE(FirebaseAnalytics.Event.PURCHASE),

    // Erros e exceções
    ERROR("app_error"),
    CRASH("app_crash"),
    EMPTY_STATE("empty_state"),

    // Novos eventos
    LOGOUT("logout"),
    SETTINGS_CHANGE("settings_change"),
    NOTIFICATION_RECEIVED("notification_received"),
    NOTIFICATION_OPENED("notification_opened"),
    TUTORIAL_STARTED("tutorial_started"),
    TUTORIAL_COMPLETED("tutorial_completed"),
    FEEDBACK_SUBMITTED("feedback_submitted"),
    PERMISSION_REQUESTED("permission_requested"),
    PERMISSION_GRANTED("permission_granted"),
    PERMISSION_DENIED("permission_denied"),
    REFRESH("refresh"),
    FAVORITE_ADDED("favorite_added"),
    FAVORITE_REMOVED("favorite_removed"),
    SHARE_SUCCESS("share_success"),
    SHARE_FAILURE("share_failure")
}
