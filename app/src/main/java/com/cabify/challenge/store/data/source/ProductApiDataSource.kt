package com.cabify.challenge.store.data.source

import com.cabify.challenge.core.api.ProductsApi
import com.cabify.challenge.store.domain.toDomain

class ProductApiDataSource(private val api: ProductsApi) {
  fun getAll() = api.getAll().map { it.toDomain() }
}
