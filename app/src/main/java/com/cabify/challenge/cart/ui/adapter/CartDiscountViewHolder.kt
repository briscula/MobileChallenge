package com.cabify.challenge.cart.ui.adapter

import android.view.View
import android.widget.TextView
import com.cabify.challenge.cart.ui.uimodel.UiCartDiscount
import com.cabify.challenge.cart.ui.uimodel.UiCartItem
import com.cabify.challenge.store.R

class CartDiscountViewHolder(view: View) : CartAdapter.BaseViewHolder(view) {
  private var tvName: TextView = view.findViewById(R.id.tvName)
  private var tvPrice: TextView = view.findViewById(R.id.tvPrice)

  override fun onBind(cartItem: UiCartItem) = with(cartItem as UiCartDiscount) {
    tvName.text = itemView.context.getString(name)
    tvPrice.text = price
  }
}
