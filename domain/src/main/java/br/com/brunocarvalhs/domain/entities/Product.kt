package br.com.brunocarvalhs.domain.entities

interface Product {
    val id: String
    val name: String
    val price: Long
    var quantity: Int

    fun toCopy(
        id: String = this.id,
        name: String = this.name,
        price: Long = this.price,
        quantity: Int = this.quantity
    ): Product
}
