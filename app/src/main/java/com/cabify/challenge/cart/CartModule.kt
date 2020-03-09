package com.cabify.challenge.cart

import com.cabify.challenge.cart.domain.DoCheckout
import com.cabify.challenge.cart.ui.CartPresenter
import org.koin.dsl.module

val checkoutModule = module {
  factory { DoCheckout(get()) }
  factory { CartPresenter(get(), get(), get()) }
}
