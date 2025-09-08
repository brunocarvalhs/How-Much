package br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.rememberPlaceholderState
import br.com.brunocarvalhs.data.model.ProductModel
import br.com.brunocarvalhs.domain.entities.Product
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ONE_INT
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ZERO_INT
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toCurrencyString

@Composable
fun ShoppingCartItem(
    product: Product? = null,
    onRemove: (() -> Unit)? = null,
    onQuantityChange: ((Int) -> Unit)? = null,
    isLoading: Boolean = false
) {
    var expanded by remember { mutableStateOf(false) }
    val placeholderState = rememberPlaceholderState(isVisible = isLoading)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    if (isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.6f)
                                .height(20.dp)
                                .placeholder(
                                    placeholderState = placeholderState,
                                    shape = MaterialTheme.shapes.small
                                )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.4f)
                                .height(16.dp)
                                .placeholder(
                                    placeholderState = placeholderState,
                                    shape = MaterialTheme.shapes.small
                                )
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.3f)
                                .height(14.dp)
                                .placeholder(
                                    placeholderState = placeholderState,
                                    shape = MaterialTheme.shapes.small
                                )
                        )
                    } else {
                        Text(product?.name.orEmpty(), style = MaterialTheme.typography.titleMedium)
                        Text(
                            text = stringResource(R.string.price) + " " + stringResource(
                                R.string.currency,
                                product?.price?.toCurrencyString().orEmpty()
                            ),
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Text(
                            "Qtd: ${product?.quantity}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                if (!isLoading) {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = if (expanded) stringResource(R.string.close_quantity_controls) else stringResource(
                                R.string.open_quantity_controls
                            )
                        )
                    }
                } else {
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small
                            )
                    )
                }
            }

            if (expanded) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (isLoading) {
                        repeat(3) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .placeholder(
                                        placeholderState = placeholderState,
                                        shape = MaterialTheme.shapes.small
                                    )
                            )
                        }
                    } else {
                        onRemove?.let {
                            IconButton(
                                onClick = {
                                    if ((product?.quantity ?: ZERO_INT) > ONE_INT)
                                        onQuantityChange?.invoke(
                                            (product?.quantity ?: ZERO_INT) - ONE_INT
                                        )
                                    else
                                        onRemove.invoke()
                                }
                            ) {
                                Icon(
                                    Icons.Filled.Delete,
                                    contentDescription = stringResource(R.string.decrease_quantity)
                                )
                            }
                        }

                        product?.let { prod ->
                            Text(
                                text = "${prod.quantity}",
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )

                            onQuantityChange?.let {
                                IconButton(
                                    onClick = { onQuantityChange(prod.quantity + ONE_INT) }
                                ) {
                                    Icon(
                                        Icons.Filled.Add,
                                        contentDescription = stringResource(R.string.increase_quantity)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingCartItemPreview() {
    var quantity by remember { mutableIntStateOf(1) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ShoppingCartItem(
            product = ProductModel(
                name = "Produto de Teste",
                quantity = quantity,
                price = 1000
            ),
            onRemove = {},
            onQuantityChange = { quantity = it },
            isLoading = false
        )

        ShoppingCartItem(
            isLoading = true // 🔹 preview do skeleton
        )
    }
}
