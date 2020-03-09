package com.cabify.challenge.store.ui

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.ProductMother
import com.cabify.challenge.store.R
import com.cabify.challenge.store.data.ProductRepository
import com.cabify.challenge.store.data.ShoppingCartRepository
import com.schibsted.spain.barista.assertion.BaristaListAssertions.assertListItemCount
import com.schibsted.spain.barista.interaction.BaristaListInteractions.clickListItemChild
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
class ProductsFragmentTest {

  @MockK
  lateinit var productRepository: ProductRepository
  @MockK(relaxed = true)
  lateinit var shoppingCartRepository: ShoppingCartRepository

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    loadKoinModules(module {
      factory(override = true) { productRepository }
      factory(override = true) { shoppingCartRepository }
    })
  }

  @Test
  fun shouldShowErrorLayoutWhenThereIsAnyError() {
    every { productRepository.getAll() } answers { Either.Left(Failure.ServerError) }

    launchFragmentInContainer<ProductsFragment>(Bundle(), R.style.AppTheme)

    onView(withId(R.id.errorLayout)).check(matches(isDisplayed()))
  }

  @Test
  fun shouldShowRecyclerViewWhenThereIsData() {
    val products = ProductMother.givenSomeProducts()
    every { productRepository.getAll() } answers { Either.Right(products) }

    launchFragmentInContainer<ProductsFragment>(Bundle(), R.style.AppTheme)

    assertListItemCount(R.id.recyclerView, products.size)
  }

  @Test
  fun shouldAddItems() {
    val products = ProductMother.givenSomeProducts()
    every { productRepository.getAll() } answers { Either.Right(products) }

    launchFragmentInContainer<ProductsFragment>(Bundle(), R.style.AppTheme)
    clickListItemChild(R.id.recyclerView, 0, R.id.addToCart)

    verify { shoppingCartRepository.addProduct(any()) }
  }
}
