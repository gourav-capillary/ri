package com.orion.ri.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object APIClient {

    private const val BASE_URL = "http://192.168.29.202:3000" // Default base URL

    fun getApiService(service: Class<APIService>): APIService {
        val restAdapter = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        return restAdapter.create(service)
    }
}

//val restAdapter = Retrofit.Builder()
//    .baseUrl(baseUrl) //passing API_URL
//    //                .addConverterFactory(new ToStringConverterFactory())
//    .addConverterFactory(gsonConverter) /* Default JSON Converter */ //                .addConverterFactory(GsonConverterFactory.create())
//    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
//    .client(httpClient.build()) //passing OkHttpClient object
//    .build()
//apiService = restAdapter.create(APIInterface::class.java)
