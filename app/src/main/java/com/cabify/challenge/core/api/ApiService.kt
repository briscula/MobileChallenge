package com.cabify.challenge.core.api

import com.cabify.challenge.core.api.model.ProductsDto
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

  @GET("/bins/4bwec")
  fun getAllProducts(): Call<ProductsDto>
}
