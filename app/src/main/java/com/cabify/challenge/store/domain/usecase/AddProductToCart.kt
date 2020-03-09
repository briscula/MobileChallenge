package com.cabify.challenge.store.domain.usecase

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.domain.UseCase
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.ShoppingCartRepository
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.ShoppingCart

class AddProductToCart(
  private val shoppingCartRepository: ShoppingCartRepository
) : UseCase<Failure, ShoppingCart>() {
  operator fun invoke(product: Product, ui: (Either<Failure, ShoppingCart>) -> Unit) {
    execute({ Either.Right(shoppingCartRepository.addProduct(product)) }, ui)
  }
}
