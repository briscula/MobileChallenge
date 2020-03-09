package com.cabify.challenge.core.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.cabify.challenge.core.db.model.ProductEntity

@Dao
interface ProductDao {
  @Insert
  fun insertAll(products: List<ProductEntity>)

  @Query("DELETE FROM PRODUCT")
  fun deleteAll()

  @Query("SELECT * FROM PRODUCT")
  fun loadAll(): List<ProductEntity>

  @Query("SELECT * FROM PRODUCT WHERE id=:id ")
  fun loadById(id: String): ProductEntity?
}
