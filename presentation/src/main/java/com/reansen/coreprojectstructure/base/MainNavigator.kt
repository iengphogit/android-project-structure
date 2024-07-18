package com.reansen.coreprojectstructure.base

import androidx.fragment.app.Fragment
import com.reansen.coreprojectstructure.R


interface MainNavigator : BaseNavigator

//Todo this class need to impl in Dagger Hilts as global.
open class MainNavigatorImpl(frg: Fragment? = null) : BaseNavigatorImpl(frg), MainNavigator {
    override val naviHostFragmentId: Int = 1

    //Todo override / implement methods navigate activity/fragment/dialog
    override fun navigateTo(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.Simple1 -> {
                //Todo navigate to fragment
            }
            else -> Unit
        }
    }

}