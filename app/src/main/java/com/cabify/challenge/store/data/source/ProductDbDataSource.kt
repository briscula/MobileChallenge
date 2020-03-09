package com.cabify.challenge.store.data.source

import com.cabify.challenge.core.db.ProductDao
import com.cabify.challenge.core.db.model.toEntity
import com.cabify.challenge.store.domain.Product
import com.cabify.challenge.store.domain.toDomain

class ProductDbDataSource(private val dao: ProductDao) {
  fun setProducts(products: List<Product>) {
    dao.deleteAll()
    val productsEntities = products.map {
      it.toEntity()
    }
    dao.insertAll(productsEntities)
  }

  fun getAll() = dao.loadAll().map { it.toDomain() }
}
