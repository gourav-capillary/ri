package com.orion.ri.api

import com.google.gson.GsonBuilder
import com.orion.ri.api.network.NoConnectivityException
import com.orion.ri.api.network.ServerResponseCode
import com.orion.ri.helper.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.ParameterizedType

abstract class CommonNetworkCallback<T> : Callback<T?> {

    private val call: Call<T>?
    private var totalRetries: Int
    private var retryCount = 0

    constructor() {
        call = null
        totalRetries = 0
    }

    constructor(call: Call<T>?) {
        this.call = call
        totalRetries = 3
    }

    constructor(call: Call<T>?, totalRetries: Int) {
        this.call = call
        this.totalRetries = totalRetries
    }

    override fun onResponse(call: Call<T?>, response: Response<T?>) {
        val responseCode = response.code()
        if (Utils.isAPISuccess(responseCode)) {
            onApiResponseSuccess(
                ApiResponse(
                    response
                )
            )
        } else if (this.call != null && retryCount++ < totalRetries) {
            retry()
        } else {
            otherServerErrorHandler(call, response)
        }
    }

    private fun retry() {
        call?.clone()?.enqueue(this)
    }

    private fun otherServerErrorHandler(call: Call<T?>, response: Response<T?>) {
        var errorApiResponse: ApiResponse<*>? = null
        try {
            val gson = GsonBuilder().create()
            var serverErrorResponse: T? = null
            try {
                serverErrorResponse = gson.fromJson(
                    response.errorBody()?.string(),
                    (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<T>
                )
            } catch (e: IOException) {
                errorApiResponse = ApiResponse<Any?>()
                if (e is NoConnectivityException) {
                    errorApiResponse?.code = e.errorCode
                } else {
                    errorApiResponse?.code = response.code()
                }
                errorApiResponse?.error = e
            } catch (e: Exception) {
                errorApiResponse = ApiResponse<Any?>()
                errorApiResponse?.code = response.code()
                errorApiResponse?.error = e
            }
            if (serverErrorResponse != null) {
                onApiResponseFailure(
                    ApiResponse(
                        response
                    )
                )
            } else {
                if (errorApiResponse == null) {
                    errorApiResponse = ApiResponse<Any?>()
//                    errorApiResponse.responseCode = ServerResponseCode.SOME_THING_WENT_WRONG
                }
                onDeviceOrApiFailure(errorApiResponse)
            }
        } catch (e: Exception) {
            errorApiResponse = ApiResponse<Any?>()
            errorApiResponse?.code = ServerResponseCode.SOME_THING_WENT_WRONG
            onDeviceOrApiFailure(errorApiResponse)
        }
    }

    override fun onFailure(call: Call<T?>, t: Throwable) {
        if (this.call != null && retryCount++ < totalRetries) {
            retry()
        } else {
            sendErrorInformationToRootRequest(call, t)
        }
    }

    private fun sendErrorInformationToRootRequest(call: Call<T?>, t: Throwable?) {
        call.cancel()
        val errorResponse: ApiResponse<*> = ApiResponse<Any?>()
        if (t is NoConnectivityException) {
            errorResponse.code = (t.errorCode)
        } else errorResponse.code =(ServerResponseCode.SOME_THING_WENT_WRONG)
        errorResponse.error =(t)
        onDeviceOrApiFailure(errorResponse)
    }
    abstract fun onApiResponseSuccess(apiResponse: ApiResponse<*>?)
    abstract fun onApiResponseFailure(apiFailure: ApiResponse<*>?)
    abstract fun onDeviceOrApiFailure(apiFailureBecsInternetOrOtherIssue: ApiResponse<*>?)

}
