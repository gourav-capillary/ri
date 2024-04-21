package com.orion.ri.api

import DataStoreHelper
import com.orion.ri.helper.Utils
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.model.response.ProjectResponse
import com.orion.ri.model.response.TaskResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                                println("FOR EMPLOYEE CALL" + DataStoreHelper.getInstance().getCurrentUserType())
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
                                val userType = DataStoreHelper.getInstance().getCurrentUserType()
                                var filteredProjects:List<ProjectResponse> = listOf()
                                if (userType == "admin"){
                                    filteredProjects = data
                                }else{
                                    filteredProjects = Utils.filterProjectsForUser(data)
                                }
                                DataStoreHelper.getInstance().saveAllProjects(Utils.sortProjectsByDeadline(filteredProjects))
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


        fun getAllTasksData() {
            CoroutineScope(Dispatchers.IO).launch {
                val apiService = APIClient.getApiService(APIService::class.java)

                val call = apiService.getAllTasks()
                call.enqueue(object : Callback<List<TaskResponse>>{
                    override fun onResponse(
                        call: Call<List<TaskResponse>>,
                        response: Response<List<TaskResponse>>
                    ) {

                        if (response.isSuccessful) {
                            val data = response.body()
                            if (data != null) {
                                val userType = DataStoreHelper.getInstance().getCurrentUserType()
                                var filteredTasks:List<TaskResponse> = listOf()
                                if (userType == "admin"){
                                    filteredTasks = data
                                }else{
                                    filteredTasks = Utils.filterTaskForUser(data)
                                }
                                DataStoreHelper.getInstance().saveAllTasks(Utils.sortTasksByDeadline(filteredTasks))
                            }
                        } else {
                            println("ERRRORORR" + response.message())
                        }
                    }

                    override fun onFailure(call: Call<List<TaskResponse>>, t: Throwable) {
                        println("FAAAAil")
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


