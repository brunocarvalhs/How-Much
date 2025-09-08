package br.com.brunocarvalhs.howmuch.app.modules.shoppingCart

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.shareText
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toCurrencyString
import br.com.brunocarvalhs.howmuch.app.modules.products.ProductFormBottomSheet
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.FinalizePurchaseDialog
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.HeaderComponent
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.ShareCartBottomSheet
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.ShoppingCartCardsPager
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.ShoppingCartItem
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.components.TokenBottomSheet

@Composable
fun ShoppingCartScreen(
    navController: NavController,
    viewModel: ShoppingCartViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsState(initial = null)

    var showProductSheet by rememberSaveable { mutableStateOf(false) }
    var showShareSheet by rememberSaveable { mutableStateOf(false) }
    var showFinalizeDialog by remember { mutableStateOf(false) }

    LaunchedEffect(uiEffect) {
        uiEffect?.let { effect ->
            when (effect) {
                is ShoppingCartUiEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is ShoppingCartUiEffect.NavigateToAddProduct -> {
                    showProductSheet = true
                }

                is ShoppingCartUiEffect.ShareCart -> {
                    showShareSheet = true
                }
            }
        }
    }

    ShoppingCartContent(
        uiState = uiState,
        onIntent = viewModel::onIntent,
        onAddToCart = { showProductSheet = true },
        onShared = { showShareSheet = true },
        onCheckout = { showFinalizeDialog = true }
    )

    if (showProductSheet && uiState.id != null) {
        ProductFormBottomSheet(
            shoppingCartId = uiState.id,
            navController = navController,
            onDismiss = { showProductSheet = false }
        )
    }

    if (showShareSheet) {
        ShareCartBottomSheet(
            token = uiState.token,
            onShareList = {
                context.shareText(
                    subject = context.getString(R.string.share_shopping_cart),
                    text = viewModel.shareCart()
                )
                showShareSheet = false
            },
            onShareToken = {
                context.shareText(
                    subject = context.getString(R.string.share_cart_token),
                    text = viewModel.shareCartToken()
                )
                showShareSheet = false
            },
            onDismiss = { showShareSheet = false }
        )
    }

    if (showFinalizeDialog) {
        FinalizePurchaseDialog(
            onDismiss = { showFinalizeDialog = false },
            onSubmit = { name, price ->
                viewModel.onIntent(
                    intent = ShoppingCartUiIntent.FinalizePurchase(
                        market = name,
                        totalPrice = price
                    )
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ShoppingCartContent(
    uiState: ShoppingCartUiState,
    onIntent: (ShoppingCartUiIntent) -> Unit,
    onAddToCart: () -> Unit = {},
    onShared: () -> Unit = {},
    onCheckout: () -> Unit = {},
) {
    var showTokenSheet by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val showTitle by remember {
        derivedStateOf {
            val firstVisibleItem = listState.firstVisibleItemIndex
            val offset = listState.firstVisibleItemScrollOffset
            firstVisibleItem > 0 || offset > 100
        }
    }

    Scaffold(
        topBar = {
            HeaderComponent(
                title = if (showTitle) stringResource(
                    R.string.currency,
                    uiState.totalPrice.toCurrencyString()
                ) else null,
                onShared = { showTokenSheet = true }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddToCart
            ) {
                Icon(Icons.Default.Add, contentDescription = stringResource(R.string.add_product))
            }
        }
    ) { paddingValues ->
        LazyColumn(
            state = listState,
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                ShoppingCartCardsPager(
                    uiState = uiState,
                    onCheckout = onCheckout,
                    onShared = onShared
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            if (uiState.isLoading) {
                items(3) {
                    ShoppingCartItem(isLoading = true)
                }
            } else {
                items(uiState.products) { product ->
                    ShoppingCartItem(
                        product = product,
                        onRemove = {
                            onIntent(ShoppingCartUiIntent.RemoveItem(product.id))
                        },
                        onQuantityChange = {
                            onIntent(ShoppingCartUiIntent.UpdateQuantity(product.id, it))
                        }
                    )
                }
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }

    TokenBottomSheet(
        showSheet = showTokenSheet,
        onDismiss = { showTokenSheet = false },
        onSubmit = { code -> onIntent(ShoppingCartUiIntent.SearchByToken(code)) }
    )
}

@Composable
@Preview(showBackground = true)
private fun ShoppingCartContentPreview() {
    ShoppingCartContent(
        uiState = ShoppingCartUiState(),
        onIntent = {}
    )
}
