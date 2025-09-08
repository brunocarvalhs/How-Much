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
import br.com.brunocarvalhs.howmuch.app.foundation.extensions.toCurrencyString
import br.com.brunocarvalhs.howmuch.app.modules.shoppingCart.ShoppingCartUiEffect.NavigateToAddProduct
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val createShoppingCartUseCase: CreateShoppingCartUseCase,
    private val observeShoppingCartUseCase: ObserveShoppingCartUseCase,
    private val enterShoppingCartWithTokenUseCase: EnterShoppingCartWithTokenUseCase,
    private val updateShoppingCartUseCase: UpdateShoppingCartUseCase,
    private val finalizePurchaseUseCase: FinalizePurchaseUseCase,
    private val cartLocalStorage: ICartLocalStorage
) : ViewModel() {

    private var cartId: String? = null
    private val _uiState = MutableStateFlow(ShoppingCartUiState())
    val uiState: StateFlow<ShoppingCartUiState> = _uiState.asStateFlow()

    private val _uiEffect = MutableSharedFlow<ShoppingCartUiEffect>()
    val uiEffect: SharedFlow<ShoppingCartUiEffect> = _uiEffect.asSharedFlow()

    init {
        initializeCart()
    }

    fun onIntent(intent: ShoppingCartUiIntent) {
        when (intent) {
            is ShoppingCartUiIntent.AddProduct -> emitEffect(
                NavigateToAddProduct(cartId)
            )

            is ShoppingCartUiIntent.RemoveItem -> removeProduct(intent.productId)
            ShoppingCartUiIntent.Retry -> reloadProducts()
            is ShoppingCartUiIntent.SearchByToken -> searchByToken(intent.token)
            is ShoppingCartUiIntent.UpdateQuantity -> updateProductQuantity(
                intent.productId,
                intent.quantity
            )

            is ShoppingCartUiIntent.FinalizePurchase -> finalizePurchase(
                intent.market,
                intent.totalPrice
            )

            is ShoppingCartUiIntent.SharedCart -> shareCart()
        }
    }

    private fun initializeCart() = viewModelScope.launch {
        _uiState.value = ShoppingCartUiState(isLoading = true)
        cartLocalStorage.getCartNow()?.let { cachedCart ->
            cartId = cachedCart.id
            observeCart(cartId = cachedCart.id)
        } ?: run {
            val newCart = ShoppingCartModel()
            cartId = newCart.id
            createAndObserveCart(newCart)
        }
    }

    private fun searchByToken(token: String) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)
        val result = enterShoppingCartWithTokenUseCase(token).getOrNull()
        if (result != null) {
            cartId = result.id
            cartLocalStorage.saveCartNow(result)
            observeCart(result.id)
        } else {
            emitEffect(ShoppingCartUiEffect.ShowError("Invalid token"))
        }
    }

    private fun createAndObserveCart(shoppingCart: ShoppingCartModel) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)
        createShoppingCartUseCase(shoppingCart)
            .onSuccess { cart ->
                cartId = cart.id
                observeCart(cart.id)
                cartLocalStorage.saveCartNow(cart)
                _uiState.value = _uiState.value.copy(isLoading = false)
            }.onFailure {
                emitEffect(ShoppingCartUiEffect.ShowError("Failed to create shopping cart"))
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
    }

    private fun observeCart(cartId: String) = viewModelScope.launch {
        observeShoppingCartUseCase(cartId).collect { cart ->
            updateUiState(
                products = cart.products,
                totalPrice = cart.recalculateTotal(),
                token = cart.token
            )
        }
    }

    private fun removeProduct(productId: String) = viewModelScope.launch {
        val currentProducts = _uiState.value.products.toMutableList()
        val productToRemove = currentProducts.find { it.id == productId }

        if (productToRemove != null) {
            currentProducts.remove(productToRemove)

            cartLocalStorage.getCartNow()?.let { cart ->
                val updatedCart = cart.toCopy(products = currentProducts)
                try {
                    updateShoppingCartUseCase(updatedCart)
                    updateUiState(products = currentProducts, isLoading = true)
                } catch (_: Exception) {
                    emitEffect(ShoppingCartUiEffect.ShowError("Failed to remove product"))
                }
            }
        } else {
            emitEffect(ShoppingCartUiEffect.ShowError("Product not found"))
        }
    }

    fun updateProductQuantity(productId: String, newQuantity: Int) = viewModelScope.launch {
        if (newQuantity < 1) return@launch

        val currentProducts = _uiState.value.products.toMutableList()
        val productIndex = currentProducts.indexOfFirst { it.id == productId }

        if (productIndex != -1) {
            val oldProduct = currentProducts[productIndex]
            val updatedProduct = oldProduct.toCopy(quantity = newQuantity)
            currentProducts[productIndex] = updatedProduct

            cartLocalStorage.getCartNow()?.let { cart ->
                val updatedCart = cart.toCopy(products = currentProducts)
                try {
                    updateShoppingCartUseCase(updatedCart)
                    updateUiState(products = currentProducts)
                } catch (_: Exception) {
                    emitEffect(ShoppingCartUiEffect.ShowError("Failed to update product quantity"))
                }
            }
        } else {
            emitEffect(ShoppingCartUiEffect.ShowError("Product not found"))
        }
    }

    private fun reloadProducts() = viewModelScope.launch {
        try {
            updateUiState(products = emptyList())
        } catch (_: Exception) {
            emitEffect(ShoppingCartUiEffect.ShowError("Failed to load products"))
        }
    }

    private fun updateUiState(
        products: List<Product>? = null,
        totalPrice: Long? = null,
        token: String? = null,
        isLoading: Boolean = false
    ) {
        val updatedProducts = products ?: _uiState.value.products
        val updatedTotalPrice = totalPrice ?: updatedProducts.sumOf { it.price * it.quantity }

        _uiState.value = _uiState.value.copy(
            isLoading = isLoading,
            id = cartId,
            products = updatedProducts,
            totalPrice = updatedTotalPrice,
            token = token ?: _uiState.value.token,
        )
    }

    private fun emitEffect(effect: ShoppingCartUiEffect) = viewModelScope.launch {
        _uiEffect.emit(effect)
    }

    fun finalizePurchase(
        market: String,
        totalPrice: Long
    ) = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(isLoading = true)
        cartId?.let { id ->
            finalizePurchaseUseCase(id, market = market, price = totalPrice).onSuccess {
                initializeCart()
                emitEffect(ShoppingCartUiEffect.ShowError("Compra finalizada com sucesso!"))
                _uiState.value = _uiState.value.copy(isLoading = false)
            }.onFailure {
                Log.e(
                    this@ShoppingCartViewModel.javaClass.simpleName,
                    "Failed to finalize purchase",
                    it
                )
                emitEffect(ShoppingCartUiEffect.ShowError("Falha ao finalizar a compra"))
                _uiState.value = _uiState.value.copy(isLoading = false)
            }
        } ?: run {
            _uiState.value = _uiState.value.copy(isLoading = false)
            emitEffect(ShoppingCartUiEffect.ShowError("Carrinho não encontrado"))
        }
    }

    fun shareCart(): String {
        val currentCart = _uiState.value
        return buildString {
            append("Meu carrinho de compras:\n\n")
            currentCart.products.forEach { product ->
                append("${product.name} x${product.quantity} - R$ ${product.price.toCurrencyString()}\n")
            }
            append("\n\nTotal: R$ ${currentCart.totalPrice.toCurrencyString()}")
        }
    }

    fun shareCartToken(): String {
        return buildString {
            append("🎉 Olha só! Você pode acessar meu carrinho de compras usando este token:\n\n")
            append(uiState.value.token.orEmpty())
            append("\n\nBasta inserir o token no app para ver todos os produtos e até adicionar os seus! 🛒")
        }
    }
}
