package com.cabify.challenge.store.data

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.source.ProductApiDataSource
import com.cabify.challenge.store.data.source.ProductDbDataSource
import com.cabify.challenge.store.domain.Product

class ProductRepository(
  private val api: ProductApiDataSource,
  private val local: ProductDbDataSource
) {
  fun getAll(): Either<Failure, List<Product>> {
    return api.getAll().fold(::onApiError, ::onApiResponse)
  }

  private fun onApiError(it: Failure): Either<Failure, List<Product>> {
    val allFromDb = local.getAll()
    return if (allFromDb.isNotEmpty()) {
      Either.Right(allFromDb)
    } else {
      Either.Left(it)
    }
  }

  private fun onApiResponse(it: List<Product>): Either.Right<List<Product>> {
    local.setProducts(it)
    return Either.Right(it)
  }
}
