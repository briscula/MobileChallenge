package com.cabify.challenge.core.ui.widget.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar.SnackbarLayout
import kotlin.math.min

class FloatingViewBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(context, attrs) {
  override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View) = dependency is SnackbarLayout

  override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
    child.translationY = min(0f, dependency.translationY - dependency.height)
    return true
  }
}
