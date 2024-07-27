package com.reansen.domain.usecase

data class UseCaseResult(
    var success: Boolean = false,
    var message: String = "",
    var errorMessage: String? = null,
    var errorCode: Int? = null,
    var responseData: Any? = null,
    var requestData: Any? = null
)