package com.cabify.challenge.store.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cabify.challenge.core.ui.widget.ColoredSnackBar
import com.cabify.challenge.store.R
import kotlinx.android.synthetic.main.fragment_products.*
import kotlinx.android.synthetic.main.view_error.*
import org.koin.android.ext.android.inject

class ProductsFragment : Fragment(), ProductsPresenter.View {

  private lateinit var productAdapter: ProductAdapter
  private val presenter: ProductsPresenter by inject()

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(R.layout.fragment_products, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.view = this
    productAdapter = ProductAdapter(object : ProductAdapter.ProductListener {
      override fun onAddProductClick(product: UiProduct) {
        presenter.onAddProductClick(product)
      }
    })
    view.findViewById<RecyclerView>(R.id.recyclerView).apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(activity)
      adapter = productAdapter
    }
    bShowCart.setOnClickListener { presenter.onCheckoutClick() }
    presenter.onProductsNeeded()
  }

  override fun showLoading() {
    recyclerView.visibility = View.GONE
    errorLayout?.visibility = View.GONE
    progressBar.visibility = View.VISIBLE
  }

  override fun showError() {
    recyclerView.visibility = View.GONE
    progressBar.visibility = View.GONE
    if (errorLayout == null) {
      vsErrorLayout.inflate()
      bTryAgain.setOnClickListener {
        presenter.onProductsNeeded()
      }
    } else {
      errorLayout.visibility = View.VISIBLE
    }
  }

  override fun showProducts(products: List<UiProduct>) {
    progressBar.visibility = View.GONE
    errorLayout?.visibility = View.GONE
    recyclerView.visibility = View.VISIBLE
    productAdapter.set(products)
  }

  override fun showAddedProduct(product: UiProduct) {
    ColoredSnackBar.showConfirmation(coordinatorLayoutProducts, getString(R.string.product_added_to_cart, product.name))
  }

  override fun showCheckoutView() {
    findNavController().navigate(R.id.action_productsFragment_to_checkoutFragment)
  }
}
