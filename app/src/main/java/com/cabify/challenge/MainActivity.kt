package com.cabify.challenge

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.cabify.challenge.store.R
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
  private lateinit var navController: NavController
  private lateinit var appBarConfiguration: AppBarConfiguration

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    navController = findNavController(R.id.navHostFragment)
    setSupportActionBar(toolbar)
    setUpNavigation()
  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
  }

  private fun setUpNavigation() {
    appBarConfiguration = AppBarConfiguration.Builder(R.id.productsFragment).build()
    setupActionBarWithNavController(navController, appBarConfiguration)
  }
}
