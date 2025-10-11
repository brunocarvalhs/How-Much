package br.com.brunocarvalhs.domain.useCases

import br.com.brunocarvalhs.domain.entities.ShoppingCart
import br.com.brunocarvalhs.domain.repository.ShoppingCartRepository
import br.com.brunocarvalhs.domain.services.ICartLocalStorage

class FinalizePurchaseUseCase(
    private val shoppingCartRepository: ShoppingCartRepository,
    private val localStorage: ICartLocalStorage
) {
    suspend operator fun invoke(id: String, market: String, price: Long): Result<ShoppingCart> =
        runCatching {
            val cart = shoppingCartRepository.findById(id)
                ?: error("Shopping cart not found")
            val finalizedCart = cart.finalizePurchase(market = market, price = price)
            shoppingCartRepository.update(finalizedCart)
            localStorage.saveCartHistory(finalizedCart)
            finalizedCart
        }
}
