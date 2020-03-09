package com.cabify.challenge.cart.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.ProductMother
import com.cabify.challenge.store.R
import com.cabify.challenge.store.data.ShoppingCartRepository
import com.cabify.challenge.store.domain.Discount
import com.cabify.challenge.store.domain.ShoppingCart
import com.cabify.challenge.store.domain.promotionstrategy.PromotionVoucherStrategy
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.assertion.BaristaVisibilityAssertions.assertDisplayed
import com.schibsted.spain.barista.interaction.BaristaClickInteractions.clickOn
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class CartFragmentTest {
  private val someProducts = ProductMother.givenSomeProducts()
  private val someDiscounts = listOf(Discount(PromotionVoucherStrategy.PROMOTION_CODE, -9f))
  private val anyAmount = 20f
  @MockK(relaxed = true)
  lateinit var shoppingCartRepository: ShoppingCartRepository
  @MockK
  lateinit var shoppingCart: ShoppingCart

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    every {
      shoppingCartRepository.get()
    } answers {
      Either.Right(shoppingCart)
    }
    every { shoppingCart.products } answers { someProducts }
    every { shoppingCart.discounts } answers { someDiscounts }
    every { shoppingCart.hasAnyProduct() } answers { true }
    every { shoppingCart.totalAmount } answers { anyAmount }
    loadKoinModules(module {
      factory(override = true) { shoppingCartRepository }
    })
  }

  @Test
  fun shouldShowEmptyCart() {
    every { shoppingCart.hasAnyProduct() } answers { false }

    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)

    assertDisplayed(R.string.empty_cart)
  }

  @Test
  fun shouldShowCartProductsAndDiscounts() {

    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)

    assertListItemCount(R.id.recyclerView, someProducts.size + someDiscounts.size)
  }

  @Test
  fun shouldShowAmountToPay() {
    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)

    assertDisplayed("%.2f".format(anyAmount) + " €")
  }

  @Test
  fun shouldShowErrorWhenPaymentFails() {
    every {
      shoppingCartRepository.performPayment()
    } answers {
      Either.Left(Failure.ServerError)
    }

    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)
    clickOn(R.id.bDoCheckout)

    assertDisplayed(R.string.error_payment_message)
  }

  @Test
  fun shouldShowPaymentFeedbackAfterSuccessfulPayment() {
    every {
      shoppingCartRepository.performPayment()
    } answers {
      Either.Right(Unit)
    }

    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)
    clickOn(R.id.bDoCheckout)

    assertDisplayed(R.string.success_payment_message)
  }

  @Test
  fun shouldEmptyCartAfterSuccessfulPayment() {
    every {
      shoppingCartRepository.performPayment()
    } answers {
      Either.Right(Unit)
    }

    launchFragmentInContainer<CartFragment>(Bundle(), R.style.AppTheme)
    clickOn(R.id.bDoCheckout)

    verify { shoppingCartRepository.emptyShoppingCart() }
  }
}
