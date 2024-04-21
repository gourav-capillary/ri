package com.orion.ri.api.network

import android.content.Context
import java.io.IOException

class NoConnectivityException(context: Context?) : IOException() {

    var errorCode: Int
        private set
    var errorMsg: String = ""
        private set

    init {
        if (context != null) {
            errorMsg = "no internet connection"
        }
        errorCode = ServerResponseCode.INTERNET_ERROR
    }
}