package com.cabify.challenge.cart.ui.uimodel

import com.cabify.challenge.store.domain.Discount
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.ShoppingCart

data class UiCart(
  val cartItems: List<UiCartItem>,
  val totalAmount: String
)

fun ShoppingCart.toUiModel(): UiCart =
  UiCart(
    buildCartItems(products, discounts),
    formatPrice(totalAmount)
  )

fun buildCartItems(products: List<Product>, discounts: List<Discount>): List<UiCartItem> {
  return products.toUiModel() + discounts.toUiModel()
}

private fun formatPrice(price: Float) = "%.2f".format(price) + " €"
