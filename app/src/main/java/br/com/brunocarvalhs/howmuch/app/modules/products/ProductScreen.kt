package br.com.brunocarvalhs.howmuch.app.modules.products

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import br.com.brunocarvalhs.howmuch.R
import br.com.brunocarvalhs.howmuch.app.foundation.constants.EMPTY_LONG
import br.com.brunocarvalhs.howmuch.app.foundation.constants.EMPTY_STRING
import br.com.brunocarvalhs.howmuch.app.foundation.constants.ONE_INT
import br.com.brunocarvalhs.howmuch.app.foundation.constants.marketItems
import br.com.brunocarvalhs.howmuch.app.modules.products.components.PriceInput
import br.com.brunocarvalhs.howmuch.app.modules.products.components.ProductNameInput
import br.com.brunocarvalhs.howmuch.app.modules.products.components.QuantitySelector
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormScreen(
    shoppingCartId: String?,
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsState(initial = null)

    LaunchedEffect(shoppingCartId) {
        viewModel.onIntent(ProductUiIntent.LoadShoppingCart(shoppingCartId))
    }

    LaunchedEffect(uiEffect) {
        uiEffect?.let { effect ->
            when (effect) {
                is ProductUiEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is ProductUiEffect.ProductAdded -> {
                    navController.popBackStack()
                }
            }
        }
    }

    ProductContent(
        uiState = uiState,
        onIntent = viewModel::onIntent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductFormBottomSheet(
    shoppingCartId: String?,
    navController: NavController,
    onDismiss: () -> Unit,
    viewModel: ProductViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiEffect by viewModel.uiEffect.collectAsState(initial = null)

    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(shoppingCartId) {
        viewModel.onIntent(ProductUiIntent.LoadShoppingCart(shoppingCartId))
    }

    LaunchedEffect(uiEffect) {
        uiEffect?.let { effect ->
            when (effect) {
                is ProductUiEffect.ShowError -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }

                is ProductUiEffect.ProductAdded -> {
                    scope.launch {
                        sheetState.hide()
                        onDismiss()
                    }
                }
            }
        }
    }

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = sheetState
    ) {
        ProductContent(
            uiState = uiState,
            onIntent = viewModel::onIntent
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductContent(
    uiState: ProductUiState,
    onIntent: (ProductUiIntent) -> Unit
) {
    val nameFocusRequester = remember { FocusRequester() }
    val priceFocusRequester = remember { FocusRequester() }

    var name by remember { mutableStateOf(EMPTY_STRING) }
    var price by remember { mutableLongStateOf(EMPTY_LONG) }
    var quantity by remember { mutableIntStateOf(ONE_INT) }

    LaunchedEffect(Unit) {
        nameFocusRequester.requestFocus()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.register_product)) })
        },
        bottomBar = {
            Button(
                onClick = {
                    onIntent(
                        ProductUiIntent.AddProduct(
                            name = name,
                            price = price,
                            quantity = quantity
                        )
                    )
                    name = EMPTY_STRING
                    price = EMPTY_LONG
                    quantity = ONE_INT
                    nameFocusRequester.requestFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(stringResource(R.string.add_product))
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp, vertical = 20.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {

            ProductNameInput(
                name = name,
                onNameChange = { name = it },
                suggestions = marketItems,
                modifier = Modifier.focusRequester(nameFocusRequester)
            )

            PriceInput(
                price = price,
                onPriceChange = { newPrice -> price = newPrice },
                modifier = Modifier.focusRequester(priceFocusRequester)
            )

            QuantitySelector(
                quantity = quantity,
                onQuantityChange = { quantity = it }
            )
        }
    }
}

