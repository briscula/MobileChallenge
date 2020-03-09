package com.cabify.challenge.store.domain

import com.cabify.challenge.store.ProductMother
import com.cabify.challenge.store.ProductMother.givenMug
import com.cabify.challenge.store.ProductMother.givenTShirt
import com.cabify.challenge.store.domain.promotionstrategy.PromotionTshirtStrategy
import com.cabify.challenge.store.domain.promotionstrategy.PromotionVoucherStrategy
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ShoppingCartTest {
  private val voucher = ProductMother.givenVoucher()
  private val tShirt = givenTShirt()
  private val mug = givenMug()
  private val promotions = listOf(
    PromotionVoucherStrategy(),
    PromotionTshirtStrategy()
  )
  private lateinit var shoppingCart: ShoppingCart

  @Test
  fun shouldSumAllPricesWhenThereAreNoDiscounts() {
    shoppingCart = ShoppingCart(listOf(voucher, tShirt, mug), promotions)

    assertEquals(32.5f, shoppingCart.totalAmount)
  }

  @Test
  fun shouldApplyVoucherDiscount() {
    shoppingCart = ShoppingCart(listOf(voucher, tShirt, voucher), promotions)

    assertEquals(25.0f, shoppingCart.totalAmount)
  }

  @Test
  fun shouldApplyTShirtDiscount() {
    shoppingCart = ShoppingCart(listOf(tShirt, tShirt, tShirt, voucher, tShirt), promotions)

    assertEquals(81.0f, shoppingCart.totalAmount)
  }

  @Test
  fun shouldApplyVoucherAndTShirtDiscount() {
    shoppingCart = ShoppingCart(listOf(voucher, tShirt, voucher, voucher, mug, tShirt, tShirt), promotions)

    assertEquals(74.5f, shoppingCart.totalAmount)
  }
}
