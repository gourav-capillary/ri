package com.orion.ri.api


interface ApiResponseListener {
    fun onSuccess(apiResponse: ApiResponse<*>?)
    fun onFailure(apiResponseFail: ApiResponse<*>?)
    fun onError(apiResponseError: ApiResponse<*>?)
}