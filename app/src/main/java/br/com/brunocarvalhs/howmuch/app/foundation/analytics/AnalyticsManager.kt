package br.com.brunocarvalhs.howmuch.app.foundation.analytics

interface AnalyticsManager {
    fun trackScreen(screenName: String)
    fun trackEvent(eventName: String, params: Map<String, Any>? = null)
}
