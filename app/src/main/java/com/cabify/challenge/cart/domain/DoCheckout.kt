package com.cabify.challenge.cart.domain

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.domain.UseCase
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.ShoppingCartRepository

class DoCheckout(
  private val repository: ShoppingCartRepository
) : UseCase<Failure, Unit>() {
  operator fun invoke(ui: (Either<Failure, Unit>) -> Unit) {
    execute({
      val paymentResult = repository.performPayment()
      if (paymentResult.isRight()) {
        repository.emptyShoppingCart()
      }
      paymentResult
    }, ui)
  }
}
