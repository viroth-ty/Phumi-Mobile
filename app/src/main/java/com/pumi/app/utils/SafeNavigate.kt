package com.pumi.app.utils

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController

@SuppressLint("RestrictedApi")
fun NavController.navigateSafe(@IdRes id: Int, bundle: Bundle? = null) {
    val currentNode = if (backStack.isEmpty()) graph else backStack.last.destination
    val action = currentNode.getAction(id) ?: return
    if (currentDestination?.id == action.destinationId) return
    navigate(id, bundle)
}
