MyApp/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/
│   │   │   │       └── example/
│   │   │   │           └── myapp/
│   │   │   │               ├── data/
│   │   │   │               │   ├── repository/
│   │   │   │               │   │   └── DataRepositoryImpl.kt
│   │   │   │               │   └── source/
│   │   │   │               │       ├── DataSource.kt
│   │   │   │               │       └── RemoteDataSource.kt
│   │   │   │               ├── domain/
│   │   │   │               │   ├── model/
│   │   │   │               │   │   └── Data.kt
│   │   │   │               │   ├── repository/
│   │   │   │               │   │   └── DataRepository.kt
│   │   │   │               │   └── usecase/
│   │   │   │               │       ├── FetchDataUseCase.kt
│   │   │   │               │       └── UseCase.kt
│   │   │   │               └── presentation/
│   │   │   │                   ├── viewmodel/
│   │   │   │                   │   └── MyViewModel.kt
│   │   │   │                   └── ui/
│   │   │   │                       └── MainActivity.kt
│   │   │   └── res/
│   │   │       ├── layout/
│   │   │       │   └── activity_main.xml
│   │   │       └── values/
│   │   │           └── strings.xml
│   ├── build.gradle
├── build.gradle
└── settings.gradle

-------- 
package com.example.myapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapp.data.repository.DataRepositoryImpl
import com.example.myapp.domain.usecase.FetchDataUseCase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observers.DisposableObserver

 class MyViewModel : ViewModel() {

    private val repository = DataRepositoryImpl()
    private val fetchDataUseCase = FetchDataUseCase(repository)
    private val disposables = CompositeDisposable()

    fun fetchData(param: String) {
        fetchDataUseCase.execute(param, object : DisposableObserver<String>() {
            override fun onNext(result: String) {
                println("Received: $result")
            }

            override fun onError(e: Throwable) {
                println("Error: ${e.message}")
            }

            override fun onComplete() {
                println("Completed")
            }
        }).also { disposables.add(it) }
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}

