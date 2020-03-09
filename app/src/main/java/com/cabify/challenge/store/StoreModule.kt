package com.cabify.challenge.store

import com.cabify.challenge.core.api.ApiClient
import com.cabify.challenge.core.api.ProductsApi
import com.cabify.challenge.core.db.AppDatabase
import com.cabify.challenge.store.data.ProductRepository
import com.cabify.challenge.store.data.ShoppingCartRepository
import com.cabify.challenge.store.data.source.ProductApiDataSource
import com.cabify.challenge.store.data.source.ProductDbDataSource
import com.cabify.challenge.store.data.source.ShoppingCartDbDataSource
import com.cabify.challenge.store.domain.promotionstrategy.PromotionTshirtStrategy
import com.cabify.challenge.store.domain.promotionstrategy.PromotionVoucherStrategy
import com.cabify.challenge.store.domain.usecase.AddProductToCart
import com.cabify.challenge.store.domain.usecase.EmptyShoppingCart
import com.cabify.challenge.store.domain.usecase.GetAllProducts
import com.cabify.challenge.store.domain.usecase.GetShoppingCart
import com.cabify.challenge.store.ui.ProductsPresenter
import org.koin.dsl.module

val storeModule = module {
  factory {
    listOf(
      PromotionTshirtStrategy(),
      PromotionVoucherStrategy()
    )
  }
  factory { EmptyShoppingCart(get()) }
  factory { GetShoppingCart(get()) }
  single { ApiClient.Builder().withEndPoint("https://api.myjson.com/").create() }
  factory { ProductsApi(get()) }
  factory { ProductApiDataSource(get()) }
  factory { ProductDbDataSource(get()) }
  factory { ShoppingCartDbDataSource(get(), get(), get()) }
  single { AppDatabase.buildDatabase(get()).shoppingCartDao() }
  single { AppDatabase.buildDatabase(get()).productDao() }
  factory { ShoppingCartRepository(get()) }
  factory { ProductRepository(get(), get()) }
  factory { AddProductToCart(get()) }
  factory { GetAllProducts(get()) }
  factory { ProductsPresenter(get(), get()) }
}
