package com.cabify.challenge.store.data

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.ProductMother.givenSomeProducts
import com.cabify.challenge.store.data.source.ProductApiDataSource
import com.cabify.challenge.store.data.source.ProductDbDataSource
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest {
  @MockK(relaxed = true)
  lateinit var api: ProductApiDataSource
  @MockK(relaxed = true)
  lateinit var local: ProductDbDataSource

  private lateinit var repository: ProductRepository

  @Before
  fun setUp() {
    MockKAnnotations.init(this)
    repository = ProductRepository(api, local)
  }

  @Test
  fun shouldGetProductsFromApiDataSource() {
    every { api.getAll() } answers { Either.Right(givenSomeProducts()) }

    val response = repository.getAll()

    assertEquals(givenSomeProducts(), response.getOrNull()!!)
  }

  @Test
  fun shouldSaveProductsFromApiDataSourceToLocalDataSource() {
    every { api.getAll() } answers { Either.Right(givenSomeProducts()) }

    repository.getAll()

    verify { local.setProducts(any()) }
  }

  @Test
  fun shouldGetProductsFromLocalDataSourceWhenApiDataSourceFails() {
    every { api.getAll() } answers { Either.Left(Failure.ServerError) }
    every { local.getAll() } answers { givenSomeProducts() }

    val response = repository.getAll()

    assertEquals(givenSomeProducts(), response.getOrNull()!!)
  }
}
