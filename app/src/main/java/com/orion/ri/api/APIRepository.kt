package com.orion.ri.api

import DataStoreHelper
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.model.response.ProjectResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIRepository {


    companion object {
        fun getAllEmployeesData() {
            CoroutineScope(Dispatchers.IO).launch {
                val apiService = APIClient.getApiService(APIService::class.java)

                val call = apiService.getAllEmployees()
                call.enqueue(object : Callback<List<EmployeesResponse>> {

                    override fun onResponse(
                        call: Call<List<EmployeesResponse>>,
                        response: Response<List<EmployeesResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (data != null) {
                                println("AOSHDOSAHDAHSOD " + data)
                                DataStoreHelper.getInstance().saveAllUsers(data)
                            }
                        } else {
                            println("ERRRORORR" + response.message())
                        }
                    }

                    override fun onFailure(call: Call<List<EmployeesResponse>>, t: Throwable) {
                        // Handle network failure
                        println("ERRRORORR2222" + t.message)

                    }
                })
            }

        }

        fun getAllProjectsData() {
            CoroutineScope(Dispatchers.IO).launch {
                val apiService = APIClient.getApiService(APIService::class.java)

                val call = apiService.getAllProjects()
                call.enqueue(object : Callback<List<ProjectResponse>> {

                    override fun onResponse(
                        call: Call<List<ProjectResponse>>,
                        response: Response<List<ProjectResponse>>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()
                            if (data != null) {
                                println("AOSHDOSAHDAHSOD " + data)
                                DataStoreHelper.getInstance().saveAllProjects(data)
                            }
                        } else {
                            println("ERRRORORR" + response.message())
                        }
                    }

                    override fun onFailure(call: Call<List<ProjectResponse>>, t: Throwable) {
                        // Handle network failure
                        println("ERRRORORR2222" + t.message)

                    }
                })
            }
        }

        fun getCurrentUserProfileByEmail(
            email: String,
            apiResponseListener: ApiResponseListener
        ) {
            CoroutineScope(Dispatchers.IO).launch {
                val apiService = APIClient.getApiService(APIService::class.java)

                val call = apiService.getUserByEmail(email)
                call.enqueue(object : CommonNetworkCallback<EmployeesResponse>() {

                    //                    override fun onResponse(
//                        call: Call<EmployeesResponse>,
//                        response: Response<EmployeesResponse>
//                    ) {
//                        if (response.isSuccessful) {
//                            val data = response.body()
//                            if (data != null) {
//                                DataStoreHelper.getInstance().saveCurrentEmployeeProfile(data)
//                                apiResponseListener.onSuccess(response.body())
//                            }
//                        } else {
//                            println("ERRRORORR" + response.message())
//                            callback(null)
//                        }
//                    }
//
//                    override fun onFailure(call: Call<EmployeesResponse>, t: Throwable) {
//                        // Handle network failure
//                        callback(null)
//                        println("ERRRORORR2222" + t.message)
//                    }

                    override fun onApiResponseSuccess(apiResponse: ApiResponse<*>?) {
                        apiResponseListener.onSuccess(apiResponse)
                    }

                    override fun onApiResponseFailure(apiFailure: ApiResponse<*>?) {
                        apiResponseListener.onFailure(apiFailure)
                    }

                    override fun onDeviceOrApiFailure(apiFailureBecsInternetOrOtherIssue: ApiResponse<*>?) {
                        apiResponseListener.onError(apiFailureBecsInternetOrOtherIssue)
                    }
                })
            }
        }

    }
}


