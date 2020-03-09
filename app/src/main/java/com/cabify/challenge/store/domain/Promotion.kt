package com.cabify.challenge.store.domain

interface Promotion {
  fun promotionCode(): String
  fun applyPromotion(products: List<Product>): Discount?
}
