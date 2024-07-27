package com.reansen.coreprojectstructure.base

import androidx.lifecycle.ViewModel
import com.reansen.domain.usecase.UseCaseParamIn
import com.reansen.domain.usecase.helloworld.HelloWorldUseCase
import io.reactivex.rxjava3.disposables.CompositeDisposable

class BaseViewModel : ViewModel() {

    private val fetchDataUseCase = HelloWorldUseCase()
    private val disposables = CompositeDisposable()
    fun doSomething() {
        val disposable = fetchDataUseCase.execute(
            UseCaseParamIn(),
            { result -> println("Received data: $result") },
            { error -> println("Error: ${error.message}") },
            { println("Completed") }
        )
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

}
