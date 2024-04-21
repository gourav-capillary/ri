package com.orion.ri.api

import retrofit2.Call
import retrofit2.Response
import java.io.IOException

class ApiResponse<T> {
    var code: Int = 0
    var body: T? = null
    var error: Throwable? = null
    var rawResponse: okhttp3.Response? = null

    constructor() {}
    constructor(error: Throwable) {
        code = 500
        body = null
        this.error = error
        rawResponse = null
    }

    constructor(response: Response<T>) {
        code = response.code()
        if (response.isSuccessful) {
            body = response.body()
            error = null
        } else {
            body = null
            error = IOException("Unexpected code $code")
        }
        rawResponse = response.raw()
    }

    constructor(call: Call<T>, t: Throwable) {
        code = 500
        body = null
        error = t
        rawResponse = null
    }

    fun isSuccess(): Boolean {
        return code in 200..299
    }

    fun isError(): Boolean {
        return error != null || !isSuccess()
    }

    fun isFailure(): Boolean {
        return error != null
    }
}
