package com.reansen.domain.usecase

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseUseCase<in Params, Result : Any> {

    protected abstract fun buildUseCaseObservable(params: Params): Observable<Result>

    fun execute(params: Params, onNext: (Result) -> Unit, onError: (Throwable) -> Unit, onComplete: () -> Unit): Disposable {
        val observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())

        return observable.subscribe(onNext, onError, onComplete)
    }
}