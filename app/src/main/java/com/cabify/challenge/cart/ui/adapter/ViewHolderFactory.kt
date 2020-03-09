package com.cabify.challenge.cart.ui.adapter

import android.view.View
import com.cabify.challenge.cart.ui.uimodel.UiCartDiscount
import com.cabify.challenge.cart.ui.uimodel.UiCartProduct

object ViewHolderFactory {
  fun holder(view: View, viewType: Int) = when (viewType) {
    UiCartDiscount.TYPE -> CartDiscountViewHolder(view)
    UiCartProduct.TYPE -> CartProductViewHolder(view)
    else -> throw IllegalArgumentException()
  }
}
