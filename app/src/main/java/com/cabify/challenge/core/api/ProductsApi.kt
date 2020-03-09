package com.cabify.challenge.core.api

import com.cabify.challenge.core.api.model.ProductsDto
import com.cabify.challenge.core.api.retrofit.run
import com.cabify.challenge.core.api.retrofit.toDomain
import com.cabify.challenge.core.domain.Failure
import com.cabify.challenge.core.type.Either

class ProductsApi(private val client: ApiClient) {

  fun getAll(): Either<Failure, ProductsDto> = with(client) {
    apiService.getAllProducts().run().toDomain()
  }
}
