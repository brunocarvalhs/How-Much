package br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material3.placeholder
import androidx.wear.compose.material3.rememberPlaceholderState
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toCurrencyString

@Composable
fun ShoppingCartInfo(
    totalPrice: Long = 0L,
    productsCount: Int = 0,
    isLoading: Boolean = false,
    onCheckout: (() -> Unit)? = null,
    onShared: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val placeholderState = rememberPlaceholderState(isVisible = isLoading)

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = stringResource(R.string.shopping_cart_icon),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(4.dp))
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(120.dp)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                } else {
                    Text(
                        text = stringResource(R.string.cart_summary),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Column {
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .height(32.dp)
                            .width(160.dp)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .height(20.dp)
                            .width(100.dp)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                } else {
                    Text(
                        text = "R$ ${totalPrice.toCurrencyString()}",
                        style = MaterialTheme.typography.headlineLarge,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = stringResource(R.string.quantity, productsCount),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (isLoading) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Box(
                        modifier = Modifier
                            .height(48.dp)
                            .weight(1f)
                            .placeholder(
                                placeholderState = placeholderState,
                                shape = MaterialTheme.shapes.small,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            )
                    )
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    onCheckout?.let {
                        Button(onClick = it, enabled = productsCount > 0) {
                            Text(text = stringResource(R.string.checkout))
                        }
                    }
                    onShared?.let {
                        Button(
                            onClick = it,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary,
                                contentColor = MaterialTheme.colorScheme.onSecondary
                            )
                        ) {
                            Text(text = stringResource(R.string.share))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShoppingCartInfoPreview() {
    ShoppingCartInfo(
        totalPrice = 123456,
        productsCount = 5,
        isLoading = false,
        onCheckout = {},
        onShared = {}
    )
}

@Preview
@Composable
private fun ShoppingCartInfoEmptyPreview() {
    ShoppingCartInfo(
        totalPrice = 0,
        productsCount = 0,
        isLoading = false,
        onCheckout = {},
        onShared = {}
    )
}

@Preview
@Composable
private fun ShoppingCartInfoNoActionsPreview() {
    ShoppingCartInfo(
        totalPrice = 98765,
        productsCount = 3,
        isLoading = false,
        onCheckout = null,
        onShared = null
    )
}

@Preview
@Composable
private fun ShoppingCartInfoNoCheckoutPreview() {
    ShoppingCartInfo(
        totalPrice = 98765,
        productsCount = 3,
        isLoading = false,
        onCheckout = null,
        onShared = {}
    )
}

@Preview
@Composable
private fun ShoppingCartInfoNoSharePreview() {
    ShoppingCartInfo(
        totalPrice = 98765,
        productsCount = 3,
        isLoading = false,
        onCheckout = {},
        onShared = null
    )
}

@Preview
@Composable
private fun ShoppingCartInfoLoadingPreview() {
    ShoppingCartInfo(
        isLoading = true,
    )
}
