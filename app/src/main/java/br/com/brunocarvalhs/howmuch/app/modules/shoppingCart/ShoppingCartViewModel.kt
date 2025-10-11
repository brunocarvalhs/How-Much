package br.com.brunocarvalhs.howmuch.app.modules.shoppingCart

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.brunocarvalhs.data.model.ShoppingCartModel
import br.com.brunocarvalhs.domain.entities.Product
import br.com.brunocarvalhs.domain.services.ICartLocalStorage
import br.com.brunocarvalhs.domain.useCases.CreateShoppingCartUseCase
import br.com.brunocarvalhs.domain.useCases.EnterShoppingCartWithTokenUseCase
import br.com.brunocarvalhs.domain.useCases.FinalizePurchaseUseCase
import br.com.brunocarvalhs.domain.useCases.ObserveShoppingCartUseCase
import br.com.brunocarvalhs.domain.useCases.UpdateShoppingCartUseCase
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.ShoppingCartUiEffect.NavigateToAddProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase,
    private val observeShoppingCartUseCase: ObserveShoppingCartUseCase,
    private val enterShoppingCartWithTokenUseCase: EnterShoppingCartWithTokenUseCase,
    private val updateShoppingCartUseCase: UpdateShoppingCartUseCase,
    private val finalizePurchaseUseCase: FinalizePurchaseUseCase,
    private val cartLocalStorage: ICartLocalStorage,
) : ViewModel() {

    private val _uiState = MutableStateFlow(ShoppingCartUiState())
    val uiState: StateFlow<ShoppingCartUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ShoppingCartUiEffect>()
    val uiEffect: SharedFlow<ShoppingCartUiEffect> = _uiEffect.asSharedFlow()

    init {
        initializeCart()
    }

    fun onIntent(intent: ShoppingCartUiIntent) {
        when (intent) {
            is ShoppingCartUiIntent.AddProduct -> onAddProduct()
            is ShoppingCartUiIntent.RemoveItem -> removeProduct(productId = intent.productId)
            ShoppingCartUiIntent.Retry -> initializeCart()
            is ShoppingCartUiIntent.SearchByToken -> searchByToken(token = intent.token)
            is ShoppingCartUiIntent.UpdateQuantity -> updateProductQuantity(
                productId = intent.productId,
                newQuantity = intent.quantity
            )

            is ShoppingCartUiIntent.FinalizePurchase -> finalizePurchase(
                market = intent.market,
                totalPrice = intent.totalPrice
            )

            is ShoppingCartUiIntent.UpdateChecked -> checkProduct(
                product = intent.product,
                price = intent.price,
                isChecked = intent.isChecked
            )
        }
    }

    private fun onAddProduct() = viewModelScope.launch {
        _uiEffect.emit(NavigateToAddProduct(uiState.value.cartId))
    }

    private fun initializeCart() = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        val cachedCart = cartLocalStorage.getCartNow()
        if (cachedCart != null) {
            observeCart(cachedCart.id)
        } else {
            createAndObserveCart()
        }
    }

    private fun searchByToken(token: String) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        enterShoppingCartWithTokenUseCase(token)
            .onSuccess { cart ->
                cartLocalStorage.saveCartNow(cart)
                observeCart(cart.id)
            }
            .onFailure {
                _uiState.update { it.copy(isLoading = false) }
                _uiEffect.emit(ShoppingCartUiEffect.ShowError("Token inválido"))
            }
    }

    private fun createAndObserveCart() = viewModelScope.launch {
        _uiState.value = ShoppingCartUiState()
        _uiState.update { it.copy(isLoading = true) }
        val newCart = ShoppingCartModel()
        createShoppingCartUseCase(newCart)
            .onSuccess { cart ->
                cartLocalStorage.saveCartNow(cart)
                observeCart(cart.id)
            }
            .onFailure {
                handleError("Falha ao criar o carrinho de compras")
            }
    }

    private fun observeCart(cartId: String) {
        observeShoppingCartUseCase(cartId)
            .onEach { cart ->
                if (cart.purchaseDate != null) {
                    cartLocalStorage.clearCart()
                    createAndObserveCart()
                    return@onEach
                }

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        cartId = cart.id,
                        products = cart.products,
                        totalPrice = cart.recalculateTotal(),
                        token = cart.token,
                        market = cart.market
                    )
                }
            }
            .catch { error ->
                Log.e(
                    this@ShoppingCartViewModel.javaClass.simpleName,
                    "Falha ao observar o carrinho com id: $cartId. Provavelmente foi deletado.",
                    error
                )
                cartLocalStorage.clearCart()
                createAndObserveCart()
            }
            .launchIn(viewModelScope)
    }

    private fun removeProduct(productId: String) = viewModelScope.launch {
        updateProductListInCart { products ->
            products.filter { it.id != productId }
        }
    }

    private fun updateProductQuantity(productId: String, newQuantity: Int) = viewModelScope.launch {
        if (newQuantity < 1) return@launch
        updateProductListInCart { products ->
            products.map { if (it.id == productId) it.toCopy(quantity = newQuantity) else it }
        }
    }

    private fun checkProduct(product: Product, price: Long, isChecked: Boolean) =
        viewModelScope.launch {
            updateProductListInCart { products ->
                products.map {
                    if (it.id == product.id) {
                        it.toCopy(price = price, isChecked = isChecked)
                    } else {
                        it
                    }
                }
            }
        }

    private suspend fun updateProductListInCart(transform: (List<Product>) -> List<Product>) {
        val cart = cartLocalStorage.getCartNow()
        if (cart == null) {
            _uiEffect.emit(ShoppingCartUiEffect.ShowError("Carrinho não encontrado"))
            createAndObserveCart()
            return
        }

        val updatedProducts = transform(cart.products).toMutableList()
        val updatedCart = cart.toCopy(products = updatedProducts)

        updateShoppingCartUseCase(updatedCart)
            .onFailure { handleError("Falha ao atualizar o produto") }
    }

    private fun finalizePurchase(market: String, totalPrice: Long) = viewModelScope.launch {
        val cartToArchive = cartLocalStorage.getCartNow()

        if (cartToArchive == null) {
            _uiEffect.emit(ShoppingCartUiEffect.ShowError("Carrinho não encontrado"))
            return@launch
        }

        _uiState.update { it.copy(isLoading = true) }

        finalizePurchaseUseCase(cartToArchive.id, market = market, price = totalPrice)
            .onSuccess {
                _uiEffect.emit(ShoppingCartUiEffect.ShowError("Compra finalizada com sucesso!"))
            }
            .onFailure {
                Log.e(
                    this@ShoppingCartViewModel.javaClass.simpleName,
                    "Failed to finalize purchase",
                    it
                )
                handleError("Falha ao finalizar a compra")
            }
    }

    private suspend fun handleError(message: String) {
        _uiState.update { it.copy(isLoading = false) }
        _uiEffect.emit(ShoppingCartUiEffect.ShowError(message))
    }
}