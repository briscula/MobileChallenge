package com.cabify.challenge.store.domain.usecase

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.domain.UseCase
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.ProductRepository
import com.cabify.challenge.store.domain.Product

class GetAllProducts(
  private val productRepository: ProductRepository
) : UseCase<Failure, List<Product>>() {
  operator fun invoke(ui: (Either<Failure, List<Product>>) -> Unit) {
    execute({ productRepository.getAll() }, ui)
  }
}
