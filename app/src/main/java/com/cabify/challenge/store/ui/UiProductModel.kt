package com.cabify.challenge.store.ui

import com.cabify.challenge.store.R
import com.cabify.challenge.store.domain.Product

data class UiProduct(
  val id: String,
  val name: String,
  val price: String,
  val image: Int
)

fun Product.toUiModel() =
  UiProduct(
    id,
    name,
    formatPrice(price),
    selectImage(id)
  )

private fun formatPrice(price: Float) = "%.2f".format(price) + " €"
fun selectImage(id: String) = when (id) {
  "VOUCHER" -> R.drawable.voucher
  "TSHIRT" -> R.drawable.tshirt
  "MUG" -> R.drawable.mug
  else -> R.drawable.ic_broken_image
}
