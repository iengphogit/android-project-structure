package com.reansen.coreprojectstructure.base

sealed class NavigationEvent {
    data class Simple1(val text: String) : NavigationEvent()

    //Purchase/Sale
    data class UIPurchaseSale(val cashierId: String) : NavigationEvent()
}