package com.cabify.challenge.core.api

import com.google.gson.GsonBuilder
import java.util.concurrent.TimeUnit
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor(private val endPoint: String) {
  companion object {
    private const val DEFAULT_TIMEOUT_IN_SECONDS = 5
  }
  private val retrofit: Retrofit by lazy {
    val builder = Retrofit.Builder()
    builder.baseUrl(this.endPoint)
    val clientBuilder = OkHttpClient.Builder()
      .readTimeout(DEFAULT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
      .writeTimeout(DEFAULT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
      .connectTimeout(DEFAULT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
      .callTimeout(DEFAULT_TIMEOUT_IN_SECONDS.toLong(), TimeUnit.SECONDS)
    builder.client(clientBuilder.build())
    builder.addConverterFactory(GsonConverterFactory.create(getGsonBuilder().create()))
    builder.build()
  }

  val apiService: ApiService by lazy { retrofit.create(ApiService::class.java) }

  private fun getGsonBuilder(): GsonBuilder = GsonBuilder()

  class Builder {
    private lateinit var endPoint: String

    fun withEndPoint(endPoint: String): Builder = apply {
      this.endPoint = endPoint
    }

    fun create(): ApiClient = ApiClient(endPoint)
  }
}
