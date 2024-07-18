package com.reansen.coreprojectstructure.base

sealed class NavigationException(cause: Throwable?) : Throwable(cause) {
    class UnknownNavigationException(currentGraph: String?, currentDestination: String?) :
        NavigationException(RuntimeException("Unknown navigation: $currentGraph -> $currentDestination"))
}