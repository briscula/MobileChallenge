package com.cabify.challenge.store.domain

import com.cabify.challenge.core.api.model.ProductsDto
import com.cabify.challenge.core.db.model.ProductEntity

data class Product(
  val id: String,
  val name: String,
  val price: Float
)

fun ProductsDto.toDomain() = products.map { Product(it.code, it.name, it.price) }
fun ProductEntity.toDomain() = Product(id, name, price)
