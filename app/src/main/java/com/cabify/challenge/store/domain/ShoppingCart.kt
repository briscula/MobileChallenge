package com.cabify.challenge.store.domain

data class ShoppingCart(
  val products: List<Product> = listOf(),
  val promotions: List<Promotion> = listOf()

) {
  val discounts: List<Discount>
  val totalAmount: Float

  init {
    discounts = applyDiscounts()
    totalAmount = products.map { it.price }.sum() + discounts.map { it.amount }.sum()
  }

  fun hasAnyProduct() = products.isNotEmpty()

  private fun applyDiscounts(): List<Discount> {
    val discounts: MutableList<Discount> = mutableListOf()
    promotions.forEach {
      it.applyPromotion(products)?.let { discounts.add(it) }
    }
    return discounts.toList()
  }
}
