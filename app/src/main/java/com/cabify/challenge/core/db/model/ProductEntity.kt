package com.cabify.challenge.core.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cabify.challenge.store.domain.Product

@Entity(tableName = "PRODUCT")
data class ProductEntity(
  @PrimaryKey
  val id: String,
  val name: String,
  val price: Float
)

fun Product.toEntity() = ProductEntity(id, name, price)
