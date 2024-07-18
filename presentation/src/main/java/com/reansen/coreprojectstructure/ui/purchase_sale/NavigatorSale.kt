package com.reansen.coreprojectstructure.ui.purchase_sale

import android.content.Context
import android.content.Intent
import com.reansen.coreprojectstructure.base.MainNavigatorImpl
import com.reansen.coreprojectstructure.base.NavigationEvent

class NavigatorSale(private val context: Context) : MainNavigatorImpl() {

    var navigationEvent: NavigationEvent? = null

    fun navigate() {
        navigationEvent?.let {
            navigateTo(it)
        }
    }

    override fun navigateTo(event: NavigationEvent) {
        when (event) {
            is NavigationEvent.UIPurchaseSale -> {
                //Todo navigate to fragment
                navigate2PurchaseSaleActivity(context)
            }

            else -> Unit
        }
    }

    private fun navigate2PurchaseSaleActivity(context: Context) {
        val intent = Intent(context, PurchaseSaleActivity::class.java)
        context.startActivity(intent)
    }

}