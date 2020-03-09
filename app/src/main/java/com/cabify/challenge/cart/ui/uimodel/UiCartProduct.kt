package com.cabify.challenge.cart.ui.uimodel

import com.cabify.challenge.store.R
import com.cabify.challenge.store.domain.Product

data class UiCartProduct(
  val id: String,
  val photo: Int,
  val name: String,
  val price: String,
  val amountOfItems: Int
) : UiCartItem {
  companion object {
    const val TYPE = R.layout.item_cart_product
  }

  override fun type(): Int = TYPE
}

fun List<Product>.toUiModel(): List<UiCartItem> =
  groupBy(Product::id).map { groupedProduct ->
    with(groupedProduct.value.first()) {
      UiCartProduct(
        id,
        selectImage(id),
        name,
        formatPrice(price),
        groupedProduct.value.count()
      )
    }
  }

private fun formatPrice(price: Float) = "%.2f".format(price) + " €"
private fun selectImage(id: String) = when (id) {
  "VOUCHER" -> R.drawable.voucher
  "TSHIRT" -> R.drawable.tshirt
  "MUG" -> R.drawable.mug
  else -> R.drawable.ic_broken_image
}
