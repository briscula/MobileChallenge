package com.cabify.challenge.store.data.source

import com.cabify.challenge.core.db.ProductDao
import com.cabify.challenge.core.db.ShoppingCartDao
import com.cabify.challenge.core.db.model.ShoppingCartEntity
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.Promotion
import com.cabify.challenge.store.domain.ShoppingCart
import com.cabify.challenge.store.domain.toDomain

class ShoppingCartDbDataSource(
  private val promotions: List<Promotion>,
  private val productDao: ProductDao,
  private val shoppingCartDao: ShoppingCartDao
) {

  fun emptyShoppingCart(): ShoppingCart {
    shoppingCartDao.deleteAll()
    return ShoppingCart(promotions = promotions)
  }

  fun getShoppingCart(): ShoppingCart {
    val productsToAddToCart = mutableListOf<Product>()
    shoppingCartDao.loadAll().forEach {
      val productEntity = productDao.loadById(it.productId)
      if (productEntity != null) {
        for (i in 1..it.amount) {
          productsToAddToCart.add(productEntity.toDomain())
        }
      }
    }
    return ShoppingCart(productsToAddToCart, promotions)
  }

  fun saveShoppingCart(shoppingCart: ShoppingCart) {
    val productsInCart = shoppingCart.products.groupBy(Product::id).map {
      ShoppingCartEntity(it.key, it.value.count())
    }

    shoppingCartDao.insert(productsInCart)
  }
}
