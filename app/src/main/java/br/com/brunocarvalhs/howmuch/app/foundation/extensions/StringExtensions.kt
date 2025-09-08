package br.com.brunocarvalhs.howmuch.app.foundation.extensions

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

fun String?.toFormatDate(
    outputFormat: DateFormat,
    inputFormat: DateFormat = DateFormat.YEAR_MONTH_DAY,
): String {
    if (this.isNullOrEmpty()) return ""
    return try {
        val parser = SimpleDateFormat(inputFormat.pattern, Locale.getDefault())
        val date = parser.parse(this)
        val formatter = SimpleDateFormat(outputFormat.pattern, Locale.getDefault())
        formatter.format(date!!)
    } catch (e: Exception) {
        ""
    }
}

enum class DateFormat(val pattern: String) {
    DAY_MONTH_YEAR("dd/MM/yyyy"),          // 07/09/2025
    YEAR_MONTH_DAY("yyyy-MM-dd"),          // 2025-09-07
    MONTH_YEAR("MM/yyyy"),                 // 09/2025
    MONTH_NAME_YEAR("MMMM/yyyy"),          // Setembro/2025
    DAY_MONTH_YEAR_DASH("dd-MM-yyyy"),     // 07-09-2025
    YEAR_MONTH_DAY_SLASH("yyyy/MM/dd"),    // 2025/09/07
    DAY_MONTH_NAME_YEAR("dd MMMM yyyy"),   // 07 Setembro 2025
    MONTH_DAY_YEAR_US("MM/dd/yyyy"),       // 09/07/2025
    DAY_MONTH_YEAR_EU("dd.MM.yyyy")        // 07.09.2025
}

fun String?.isWithinLastDays(days: Int): Boolean {
    if (this.isNullOrEmpty()) return false
    return try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = sdf.parse(this) ?: return false
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -days)
        date.after(calendar.time)
    } catch (e: Exception) {
        false
    }
}

fun String?.toLocalDate(): LocalDate? {
    return try {
        this?.let { LocalDate.parse(it) }
    } catch (e: Exception) {
        null
    }
}

