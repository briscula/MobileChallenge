package com.cabify.challenge.core.ui.widget

import android.view.View
import com.cabify.challenge.store.R
import com.google.android.material.snackbar.Snackbar

object ColoredSnackBar {
  fun showConfirmation(rootView: View, string: String) {
    val snackBar = Snackbar.make(rootView, string, Snackbar.LENGTH_LONG)
    snackBar.view.setBackgroundResource(R.color.colorSnackBarConfirmation)
    snackBar.show()
  }
}
