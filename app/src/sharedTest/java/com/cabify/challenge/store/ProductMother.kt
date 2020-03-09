package com.cabify.challenge.store

import com.cabify.challenge.store.domain.Product

object ProductMother {
  fun givenVoucher() = Product("VOUCHER", "", 5.0f)
  fun givenTShirt() = Product("TSHIRT", "", 20.0f)
  fun givenMug() = Product("MUG", "", 7.5f)

  fun givenSomeProducts() = listOf(
    givenMug(),
    givenTShirt(),
    givenVoucher()
  )
}
