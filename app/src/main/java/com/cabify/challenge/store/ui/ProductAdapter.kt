package com.cabify.challenge.store.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cabify.challenge.store.R

class ProductAdapter(
  private val listener: ProductListener
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

  interface ProductListener {
    fun onAddProductClick(product: UiProduct)
  }

  private var products: List<UiProduct> = emptyList()

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    return ViewHolder(view)
  }

  override fun getItemCount(): Int = products.size

  override fun onBindViewHolder(holder: ViewHolder, position: Int) {
    with(products[position]) {
      holder.image.setImageResource(image)
      holder.name.text = name
      holder.price.text = price
      holder.addToCart.setOnClickListener { listener.onAddProductClick(this) }
    }
  }

  fun set(products: List<UiProduct>) {
    this.products = products
    notifyDataSetChanged()
  }

  class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var image: ImageView = view.findViewById(R.id.ivPhoto)
    var name: TextView = view.findViewById(R.id.tvName)
    var price: TextView = view.findViewById(R.id.tvPrice)
    var addToCart: ImageView = view.findViewById(R.id.addToCart)
  }
}
