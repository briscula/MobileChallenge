package com.cabify.challenge.store.domain.promotionstrategy

import com.cabify.challenge.store.domain.Discount
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.Promotion

class PromotionTshirtStrategy : Promotion {
  companion object {
    const val PROMOTION_CODE = "BULK"
    const val CODE_TO_FIND = "TSHIRT"
    const val PRICE_WHEN_APPLIED = 19.0f
    private const val MIN_T_SHIRTS = 3
  }

  override fun promotionCode() = PROMOTION_CODE

  override fun applyPromotion(products: List<Product>): Discount? {
    val amountOfTShirts = products.count { product -> product.id == CODE_TO_FIND }
    if (amountOfTShirts < MIN_T_SHIRTS) {
      return null
    }
    val price = products.first { it.id == CODE_TO_FIND }.price

    val normalPrice = price * amountOfTShirts
    val reducedPrice = PRICE_WHEN_APPLIED * amountOfTShirts
    val discount = normalPrice - reducedPrice

    return Discount(PROMOTION_CODE, discount.unaryMinus())
  }
}
