package com.reansen.domain.usecase.helloworld

import com.reansen.domain.usecase.BaseUseCase
import com.reansen.domain.usecase.UseCaseParamIn
import com.reansen.domain.usecase.UseCaseResult
import io.reactivex.rxjava3.core.Observable

class HelloWorldUseCase : BaseUseCase<UseCaseParamIn, UseCaseResult>() {

    override fun buildUseCaseObservable(params: UseCaseParamIn): Observable<UseCaseResult> {
        val result = UseCaseResult()
        result.success = true
        result.message = "Hello World"
        result.responseData = "hi"
        result.requestData = params
        return Observable.just(result)
    }


}