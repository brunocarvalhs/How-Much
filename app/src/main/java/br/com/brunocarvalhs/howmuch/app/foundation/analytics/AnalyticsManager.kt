package br.com.brunocarvalhs.howmuch.app.foundation.analytics

interface AnalyticsManager {
    fun trackScreen(screenName: String)
    fun trackEvent(event: AnalyticsEvent, params: Map<AnalyticsParam, Any>? = null)
}
