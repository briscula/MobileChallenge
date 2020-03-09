package com.cabify.challenge.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cabify.challenge.core.db.model.ProductEntity
import com.cabify.challenge.core.db.model.ShoppingCartEntity

@Database(
  entities = [ProductEntity::class, ShoppingCartEntity::class],
  version = 1,
  exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
  abstract fun productDao(): ProductDao
  abstract fun shoppingCartDao(): ShoppingCartDao

  companion object {
    private const val DATABASE_NAME = "store"
    fun buildDatabase(context: Context) =
      Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        DATABASE_NAME
      ).build()
  }
}
