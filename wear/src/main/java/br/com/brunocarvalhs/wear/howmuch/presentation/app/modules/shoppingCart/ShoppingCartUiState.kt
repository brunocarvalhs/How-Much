package br.com.brunocarvalhs.wear.howmuch.presentation.app.modules.shoppingCart

import br.com.brunocarvalhs.domain.entities.Product


data class ShoppingCartUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val totalPrice: Long = 0L,
    val token: String? = null
)