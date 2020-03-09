package com.cabify.challenge.store.domain.promotionstrategy

import com.cabify.challenge.store.domain.Discount
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.Promotion

class PromotionVoucherStrategy : Promotion {
  companion object {
    const val PROMOTION_CODE = "2X1"
    const val CODE_TO_FIND = "VOUCHER"
  }

  override fun promotionCode() = PROMOTION_CODE

  override fun applyPromotion(products: List<Product>): Discount? {
    val amountOfVouchers = products.count { it.id == CODE_TO_FIND }
    if (amountOfVouchers <= 1) {
      return null
    }
    val price = products.first { it.id == CODE_TO_FIND }.price
    val applicableVouchers = if (amountOfVouchers % 2 == 0) {
      amountOfVouchers
    } else {
      amountOfVouchers - 1
    }

    val discount = price * applicableVouchers / 2
    return Discount(PROMOTION_CODE, discount.unaryMinus())
  }
}
