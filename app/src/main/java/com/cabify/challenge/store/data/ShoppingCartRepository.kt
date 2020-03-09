package com.cabify.challenge.store.data

import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either
import com.cabify.challenge.store.data.source.ShoppingCartDbDataSource
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.ShoppingCart
import kotlin.random.Random

class ShoppingCartRepository(
  private val local: ShoppingCartDbDataSource
) {

  fun emptyShoppingCart(): Either<Failure, ShoppingCart> = Either.Right(local.emptyShoppingCart())

  fun get(): Either<Failure, ShoppingCart> = Either.Right(local.getShoppingCart())

  fun addProduct(product: Product): ShoppingCart {
    val shoppingCart = local.getShoppingCart()
    val productsInCart = shoppingCart.products.toMutableList()
    productsInCart.add(product)
    val updatedCart = ShoppingCart(productsInCart, shoppingCart.promotions)
    local.saveShoppingCart(updatedCart)
    return updatedCart
  }

  fun performPayment(): Either<Failure, Unit> {
    if (Random(System.currentTimeMillis()).nextBoolean()) {
      return Either.Left(Failure.ServerError)
    }
    return Either.Right(Unit)
  }
}
