package com.cabify.challenge.cart.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.cabify.challenge.cart.ui.adapter.CartAdapter
import com.cabify.challenge.cart.ui.uimodel.UiCart
import com.cabify.challenge.store.R
import kotlinx.android.synthetic.main.fragment_cart_checkout.*
import org.koin.android.ext.android.inject

class CartFragment : Fragment(), CartPresenter.View {
  private lateinit var cartAdapter: CartAdapter
  private val presenter: CartPresenter by inject()
  private var showClearCartMenu = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    setHasOptionsMenu(true)
    return inflater.inflate(R.layout.fragment_cart_checkout, container, false)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    presenter.view = this
    cartAdapter = CartAdapter()
    recyclerView.apply {
      setHasFixedSize(true)
      layoutManager = LinearLayoutManager(activity)
      adapter = cartAdapter
    }
    bDoCheckout.setOnClickListener { presenter.onPaymentRequest() }
    presenter.onShowCart()
  }

  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
    if (showClearCartMenu) {
      inflater.inflate(R.menu.shopping_cart_menu, menu)
    }
  }

  override fun onOptionsItemSelected(item: MenuItem) =
    when (item.itemId) {
      R.id.menu_empty_cart -> {
        presenter.onClearShoppingCart()
        true
      }
      else -> super.onOptionsItemSelected(item)
    }

  override fun showEmptyCart() {
    bDoCheckout.visibility = View.GONE
    tvEmptyCart.visibility = View.VISIBLE
    showClearCartMenu = false
    activity?.invalidateOptionsMenu()
    cartAdapter.set(emptyList())
  }

  override fun showCart(cart: UiCart) {
    bDoCheckout.visibility = View.VISIBLE
    tvEmptyCart.visibility = View.GONE
    showClearCartMenu = true
    activity?.invalidateOptionsMenu()
    cartAdapter.set(cart.cartItems)
    with(bDoCheckout) {
      text = cart.totalAmount
      setOnClickListener { presenter.onPaymentRequest() }
    }
  }

  override fun showPaymentError() {
    activity?.let {
      AlertDialog.Builder(it)
        .setTitle(getString(R.string.error_payment_tittle))
        .setMessage(getString(R.string.error_payment_message))
        .setPositiveButton(android.R.string.ok, null)
        .create().show()
    }
  }

  override fun showPaymentSuccess() {
    activity?.let {
      AlertDialog.Builder(it)
        .setTitle(getString(R.string.success_payment_tittle))
        .setMessage(getString(R.string.success_payment_message))
        .setPositiveButton(android.R.string.ok, null)
        .create().show()
    }
  }
}
