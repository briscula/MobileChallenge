package com.cabify.challenge.store.domain.usecase

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.domain.UseCase
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.ShoppingCartRepository
import com.cabify.challenge.store.domain.ShoppingCart

class GetShoppingCart(
  private val shoppingCartRepository: ShoppingCartRepository
) : UseCase<Failure, ShoppingCart>() {
  operator fun invoke(ui: (Either<Failure, ShoppingCart>) -> Unit) {
    execute({ shoppingCartRepository.get() }, ui)
  }
}
