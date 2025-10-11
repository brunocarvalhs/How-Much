package br.com.brunocarvalhs.domain.services

import br.com.brunocarvalhs.domain.entities.ShoppingCart

interface ICartLocalStorage {
    suspend fun saveCartHistory(cart: ShoppingCart)
    suspend fun clearCart()
    suspend fun getCartHistory(): List<ShoppingCart>
    suspend fun clearCartHistory()
    suspend fun getCartNow(): ShoppingCart?

    suspend fun saveCartNow(cart: ShoppingCart)
    suspend fun removeCartHistory(cart: ShoppingCart)
}
