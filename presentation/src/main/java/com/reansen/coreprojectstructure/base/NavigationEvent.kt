package com.reansen.coreprojectstructure.base

import android.icu.util.Currency

sealed class NavigationEvent {
    data class Simple1(val text: String) : NavigationEvent()

    //Purchase/Sale
    data class UIPurchaseSale(
        val defaultAmount: Double = 0.00,
        val defaultCcy: Currency = Currency.getInstance("USD")
    ) : NavigationEvent()
}