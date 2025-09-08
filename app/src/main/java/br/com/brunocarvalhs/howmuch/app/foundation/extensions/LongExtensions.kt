package br.com.brunocarvalhs.howmuch.app.foundation.extensions

import java.util.Locale

fun Long.toCurrencyString(): String {
    val value = this.toDouble() / 100
    return String.format(Locale.getDefault(), "%.2f", value).replace('.', ',')
}