package br.com.brunocarvalhs.domain.useCases

import br.com.brunocarvalhs.domain.exceptions.ShoppingCartNotFoundException
import br.com.brunocarvalhs.domain.repository.ShoppingCartRepository

class DeleteShoppingCartUseCase(
    private val repository: ShoppingCartRepository
) {
    suspend operator fun invoke(cartId: String): Result<Unit> {
        return try {
            val success = repository.delete(cartId)
            if (success) Result.success(Unit)
            else throw ShoppingCartNotFoundException(cartId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
