package br.com.brunocarvalhs.howmuch.app.foundation.formatted
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import java.text.NumberFormat
import java.util.Locale

class CurrencyVisualTransformation(
    locale: Locale = Locale("pt", "BR")
) : VisualTransformation {

    private val formatter = NumberFormat.getCurrencyInstance(locale)

    override fun filter(text: AnnotatedString): TransformedText {
        val cleanText = text.text.replace("[^0-9]".toRegex(), "")
        val parsed = cleanText.toDoubleOrNull() ?: 0.0
        val formatted = formatter.format(parsed / 100)

        return TransformedText(
            AnnotatedString(formatted),
            object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = formatted.length
                override fun transformedToOriginal(offset: Int): Int = cleanText.length
            }
        )
    }
}
