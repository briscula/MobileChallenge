package com.cabify.challenge.core.api

import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ProductsApiTest : MockWebServerTest() {
  private lateinit var api: ProductsApi

  @Before
  override fun setUp() {
    super.setUp()
    val mockWebServerEndpoint = baseEndpoint
    val apiConfig = ApiClient.Builder().withEndPoint(mockWebServerEndpoint).create()
    api = ProductsApi(apiConfig)
  }

  @Test
  fun `should send findCampaign to correct endpoint`() = runBlocking {
    enqueueMockResponse(fileName = "products.json")
    api.getAll()
    assertGetRequestSentTo("/bins/4bwec")
  }

  @Test
  fun `should parse findCampaign response properly`() = runBlocking {
    enqueueMockResponse(fileName = "products.json")

    val result = api.getAll()

    val products = result.getOrNull()!!
    assert(products.products.isNotEmpty())
  }
}
