package br.com.brunocarvalhs.howmuch.app.foundation.extensions

import android.content.Context
import android.content.Intent

internal fun Context.shareText(subject: String, text: String) {
    val intent = Intent().shareText(subject = subject, text = text)
    val chooser = Intent.createChooser(intent, null)
    this.startActivity(chooser)
}

internal fun Context.sendEmail(subject: String, body: String) {
    val intent = Intent().senderEmail(subject = subject, body = body)
    val chooser = Intent.createChooser(intent, null)
    this.startActivity(chooser)
}

internal fun Context.openUrl(url: String) {
    val intent = Intent().openUrl(url = url)
    this.startActivity(intent)
}

internal fun Context.dialPhoneNumber(phoneNumber: String) {
    val intent = Intent().dialPhoneNumber(phoneNumber = phoneNumber)
    this.startActivity(intent)
}

internal fun Context.sendSms(phoneNumber: String, message: String) {
    val intent = Intent().sendSms(phoneNumber = phoneNumber, message = message)
    this.startActivity(intent)
}

internal fun Context.openAppSettings() {
    val intent = Intent().openAppSettings(packageName = this.packageName)
    this.startActivity(intent)
}

internal fun Context.openPlayStore() {
    val intent = Intent().openPlayStore(packageName = this.packageName)
    this.startActivity(intent)
}

internal fun Context.getAppVersion(): String {
    return try {
        val packageInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        packageInfo.versionName ?: "1.0"
    } catch (e: Exception) {
        "1.0"
    }
}

internal fun Context.isDeveloperModeEnabled(): Boolean {
    return try {
        val devOptions = android.provider.Settings.Secure.getInt(
            this.contentResolver,
            android.provider.Settings.Global.DEVELOPMENT_SETTINGS_ENABLED
        )
        devOptions == 1
    } catch (e: Exception) {
        false
    }
}