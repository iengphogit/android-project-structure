package com.reansen.coreprojectstructure.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable

open class BaseViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    protected fun executeObservable(execute: Disposable) {
        disposables.add(execute)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}
