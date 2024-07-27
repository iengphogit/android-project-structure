package com.reansen.coreprojectstructure.ui.hello_world

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.reansen.coreprojectstructure.base.BaseViewModel
import com.reansen.domain.usecase.UseCaseParamIn
import com.reansen.domain.usecase.helloworld.HelloWorldUseCase

class HelloWorldViewModel : BaseViewModel() {
    private val fetchDataUseCase = HelloWorldUseCase()

    private val _mutable = MutableLiveData<HelloWorldState>()
    val helloLiveData: LiveData<HelloWorldState> = _mutable

    init {
        fetchDataUseCase.apply {
            onNext = {
                println("onNext")
            }
            onError = {
                println("Throwable: ${it.message}")
            }
            onComplete = {
                println("onComplete")
                _mutable.postValue(HelloWorldState("Success on completed"))
            }
        }
    }

    fun doSomething() {
        executeObservable(fetchDataUseCase.execute(UseCaseParamIn()))
    }


}