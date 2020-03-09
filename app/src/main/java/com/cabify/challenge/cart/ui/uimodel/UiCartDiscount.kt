package com.cabify.challenge.cart.ui.uimodel

import com.cabify.challenge.store.R
import com.cabify.challenge.store.domain.Discount
import com.cabify.challenge.store.domain.promotionstrategy.PromotionTshirtStrategy
import com.cabify.challenge.store.domain.promotionstrategy.PromotionVoucherStrategy

data class UiCartDiscount(
  val id: String,
  val name: Int,
  val price: String
) : UiCartItem {
  companion object {
    const val TYPE = R.layout.item_cart_discount
  }

  override fun type(): Int = TYPE
}

fun List<Discount>.toUiModel(): List<UiCartItem> = groupBy(Discount::id).map {
  with(it.value.first()) {
    UiCartDiscount(
      id,
      selectName(id),
      formatPrice(amount)
    )
  }
}

private fun formatPrice(price: Float) = "%.2f".format(price) + " €"
private fun selectName(id: String) = when (id) {
  PromotionVoucherStrategy.PROMOTION_CODE -> R.string.discount_voucher_2x1
  PromotionTshirtStrategy.PROMOTION_CODE -> R.string.discount_tshirt_bulk
  else -> 0
}
