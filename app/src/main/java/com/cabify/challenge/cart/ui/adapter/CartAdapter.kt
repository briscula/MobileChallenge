package com.cabify.challenge.cart.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cabify.challenge.cart.ui.uimodel.UiCartItem
import java.util.Collections.emptyList

class CartAdapter : RecyclerView.Adapter<CartAdapter.BaseViewHolder>() {

  private var cartItems: List<UiCartItem> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
    return ViewHolderFactory.holder(view, viewType)
  }

  override fun getItemCount(): Int = cartItems.size

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    holder.onBind(cartItems[position])
  }

  override fun getItemViewType(position: Int): Int {
    return cartItems[position].type()
  }

  fun set(cartItems: List<UiCartItem>) {
    this.cartItems = cartItems
    notifyDataSetChanged()
  }

  abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun onBind(cartItem: UiCartItem)
  }
}
