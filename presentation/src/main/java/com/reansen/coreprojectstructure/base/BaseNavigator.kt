package com.reansen.coreprojectstructure.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

interface BaseNavigator {
    val naviHostFragmentId: Int
    fun navigatorController(): NavController?
    fun navigateTo(event: NavigationEvent)
    //..more..

}

abstract class BaseNavigatorImpl(protected val fragment: Fragment?) : BaseNavigator {
    private var navController: NavController? = null
    override fun navigatorController(): NavController? {
        return navController ?: try {
            fragment?.findNavController().apply {
                navController = this
            }
        } catch (e: IllegalStateException) {
            e.printStackTrace()
            null
        }
    }
}