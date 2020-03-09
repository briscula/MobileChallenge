package com.cabify.challenge.store.ui

import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.usecase.AddProductToCart
import com.cabify.challenge.store.domain.usecase.GetAllProducts

class ProductsPresenter(
  private val getAllProducts: GetAllProducts,
  private val addProductToCart: AddProductToCart
) {

  lateinit var view: View
  private var products: List<Product> = emptyList()

  fun onProductsNeeded() {
    view.showLoading()

    getAllProducts {
      it.fold(
        {
          view.showError()
        }, {
          products = it
          view.showProducts(products.map { it.toUiModel() })
        }
      )
    }
  }

  fun onAddProductClick(uiProduct: UiProduct) {
    val product = products.first { it.id == uiProduct.id }
    addProductToCart(product) {}
    view.showAddedProduct(uiProduct)
  }

  fun onCheckoutClick() {
    view.showCheckoutView()
  }

  interface View {
    fun showLoading()
    fun showError()
    fun showProducts(products: List<UiProduct>)
    fun showAddedProduct(product: UiProduct)
    fun showCheckoutView()
  }
}
