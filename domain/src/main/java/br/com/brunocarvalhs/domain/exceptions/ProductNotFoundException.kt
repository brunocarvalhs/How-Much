package br.com.brunocarvalhs.domain.exceptions

class ProductNotFoundException(productId: String) :
    Exception("Product with id $productId not found in cart")
