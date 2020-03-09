package com.cabify.challenge.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.cabify.challenge.core.db.model.ShoppingCartEntity

@Dao
interface ShoppingCartDao {
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insert(items: List<ShoppingCartEntity>)

  @Query("DELETE FROM SHOPPING_CART")
  fun deleteAll()

  @Query("SELECT * FROM SHOPPING_CART")
  fun loadAll(): List<ShoppingCartEntity>
}
