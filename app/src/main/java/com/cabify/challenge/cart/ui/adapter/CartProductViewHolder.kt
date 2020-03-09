package com.cabify.challenge.cart.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.cabify.challenge.cart.ui.uimodel.UiCartItem
import com.cabify.challenge.cart.ui.uimodel.UiCartProduct
import com.cabify.challenge.store.R

class CartProductViewHolder(view: View) : CartAdapter.BaseViewHolder(view) {

  private var ivPhoto: ImageView = view.findViewById(R.id.ivPhoto)
  private var tvName: TextView = view.findViewById(R.id.tvName)
  private var tvPrice: TextView = view.findViewById(R.id.tvPrice)
  private var tvAmountOfItems: TextView = view.findViewById(R.id.tvAmountOfItems)
  override fun onBind(cartItem: UiCartItem) = with(cartItem as UiCartProduct) {
    ivPhoto.setImageResource(photo)
    tvName.text = name
    tvPrice.text = price
    tvAmountOfItems.text = "x$amountOfItems"
  }
}
