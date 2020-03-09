package com.cabify.challenge.cart.ui

import com.cabify.challenge.cart.domain.DoCheckout
import com.cabify.challenge.cart.ui.uimodel.UiCart
import com.cabify.challenge.cart.ui.uimodel.toUiModel
import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.store.domain.usecase.EmptyShoppingCart
import com.cabify.challenge.store.domain.usecase.GetShoppingCart

class CartPresenter(
  private val getShoppingCart: GetShoppingCart,
  private val emptyShoppingCart: EmptyShoppingCart,
  private val doCheckout: DoCheckout
) {
  lateinit var view: View

  fun onShowCart() {
    getShoppingCart { result ->
      result.fold({}, { shoppingCart ->
        if (shoppingCart.hasAnyProduct()) {
          view.showCart(shoppingCart.toUiModel())
        } else {
          view.showEmptyCart()
        }
      })
    }
  }

  private val pass = fun(_: Failure) {}
  fun onClearShoppingCart() {
    emptyShoppingCart {
      it.fold(pass, {
        view.showEmptyCart()
      })
    }
  }

  fun onPaymentRequest() {
    doCheckout {
      it.fold(
        ::onPaymentFailure,
        ::onPaymentSuccess
      )
    }
  }

  private fun onPaymentSuccess(it: Unit) {
    view.showPaymentSuccess()
    view.showEmptyCart()
  }

  private fun onPaymentFailure(failure: Failure) {
    view.showPaymentError()
  }

  interface View {
    fun showEmptyCart()
    fun showCart(cart: UiCart)
    fun showPaymentError()
    fun showPaymentSuccess()
  }
}
