package br.com.brunocarvalhs.howmuch.app.modules.products.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.constants.EMPTY_STRING
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ONE_INT

@Composable
fun QuantitySelector(
    quantity: Int,
    onQuantityChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var textValue by remember { mutableStateOf(quantity.toString()) }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                if (quantity > ONE_INT) {
                    onQuantityChange(quantity - ONE_INT)
                    textValue = (quantity - ONE_INT).toString()
                }
            },
            enabled = quantity > ONE_INT
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = stringResource(R.string.decrease_quantity)
            )
        }

        OutlinedTextField(
            value = textValue,
            onValueChange = { newValue ->
                val num = newValue.toIntOrNull()
                if (num != null && num >= ONE_INT) {
                    textValue = num.toString()
                    onQuantityChange(num)
                } else if (newValue.isEmpty()) {
                    textValue = EMPTY_STRING
                }
            },
            singleLine = true,
            textStyle = androidx.compose.ui.text.TextStyle(
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.width(80.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            )
        )

        IconButton(
            onClick = {
                onQuantityChange(quantity + ONE_INT)
                textValue = (quantity + ONE_INT).toString()
            }
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = stringResource(R.string.increase_quantity)
            )
        }
    }
}
