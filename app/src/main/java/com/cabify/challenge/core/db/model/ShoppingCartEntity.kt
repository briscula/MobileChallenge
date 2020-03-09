package com.cabify.challenge.core.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SHOPPING_CART")
data class ShoppingCartEntity(
  @PrimaryKey
  val productId: String,
  val amount: Int
)
