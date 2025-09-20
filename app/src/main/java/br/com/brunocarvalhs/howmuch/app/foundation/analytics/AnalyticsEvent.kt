package br.com.brunocarvalhs.howmuch.app.foundation.analytics

import com.google.firebase.analytics.FirebaseAnalytics

enum class AnalyticsEvent(val eventName: String) {
    APP_OPEN("app_open"),
    FIRST_OPEN("first_open"),
    APP_BACKGROUND("app_background"),
    APP_FOREGROUND("app_foreground"),
    SCREEN_VIEW(FirebaseAnalytics.Event.SCREEN_VIEW),
    CLICK("ui_click"),
    ERROR("app_error"),
    CRASH("app_crash"),
    EMPTY_STATE("empty_state"),
    LIFECYCLE("lifecycle")
}
