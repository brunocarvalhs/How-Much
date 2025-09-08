package br.com.brunocarvalhs.howmuch.app.foundation.extensions

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri

internal fun Intent.senderEmail(subject: String, body: String) = apply {
    this.action = Intent.ACTION_SEND
    this.type = "message/rfc822"
    this.putExtra(Intent.EXTRA_EMAIL, arrayOf(""))
    this.putExtra(Intent.EXTRA_SUBJECT, subject)
    this.putExtra(Intent.EXTRA_TEXT, body)
}

@SuppressLint("UseKtx")
internal fun Intent.openUrl(url: String) = apply {
    this.action = Intent.ACTION_VIEW
    this.data = Uri.parse(url)
}

@SuppressLint("UseKtx")
internal fun Intent.dialPhoneNumber(phoneNumber: String) = apply {
    this.action = Intent.ACTION_DIAL
    this.data = Uri.parse("tel:$phoneNumber")
}

@SuppressLint("UseKtx")
internal fun Intent.sendSms(phoneNumber: String, message: String) = apply {
    this.action = Intent.ACTION_SENDTO
    this.data = Uri.parse("smsto:$phoneNumber")
    this.putExtra("sms_body", message)
}

@SuppressLint("UseKtx")
internal fun Intent.openAppSettings(packageName: String) = apply {
    this.action = android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    this.data = Uri.parse("package:$packageName")
}

@SuppressLint("UseKtx")
internal fun Intent.openPlayStore(packageName: String) = apply {
    this.action = Intent.ACTION_VIEW
    this.data = Uri.parse("market://details?id=$packageName")
}

internal fun Intent.shareText(subject: String, text: String) = apply {
    this.action = Intent.ACTION_SEND
    this.type = "text/plain"
    this.putExtra(Intent.EXTRA_SUBJECT, subject)
    this.putExtra(Intent.EXTRA_TEXT, text)
}