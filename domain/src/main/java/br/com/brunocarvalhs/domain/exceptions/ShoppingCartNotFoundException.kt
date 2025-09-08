package br.com.brunocarvalhs.domain.exceptions

class ShoppingCartNotFoundException(cartId: String) :
    Exception("ShoppingCart with id $cartId not found")
