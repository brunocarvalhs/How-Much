package br.com.brunocarvalhs.domain.useCases

import br.com.brunocarvalhs.domain.entities.ShoppingCart
import br.com.brunocarvalhs.domain.exceptions.ProductNotFoundException
import br.com.brunocarvalhs.domain.repository.ShoppingCartRepository

class RemoveProductFromCartUseCase(
    private val repository: ShoppingCartRepository
) {
    suspend operator fun invoke(cartId: String, productId: String): Result<ShoppingCart> {
        return try {
            val updatedCart = repository.removeProduct(cartId, productId)
                ?: throw ProductNotFoundException(productId)
            Result.success(updatedCart)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
