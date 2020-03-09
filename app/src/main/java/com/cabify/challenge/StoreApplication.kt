package com.cabify.challenge

import android.app.Application
import com.cabify.challenge.cart.checkoutModule
import com.cabify.challenge.store.storeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StoreApplication : Application() {
  override fun onCreate() {
    super.onCreate()
    startKoin {
      androidContext(this@StoreApplication)
      modules(listOf(storeModule, checkoutModule))
    }
  }
}
