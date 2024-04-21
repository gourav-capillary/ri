package com.orion.ri.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.orion.ri.api.APIRepository
import com.orion.ri.api.ApiResponse
import com.orion.ri.api.ApiResponseListener
import com.orion.ri.model.response.EmployeesResponse
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

open class CommonViewModel : ViewModel() {
    private val apiRepository = APIRepository
    var currentProfileLiveData: MutableLiveData<ApiResponse<*>?>? = null


    fun getAllEmployees() {
        apiRepository.getAllEmployeesData()
    }

    fun getAllProjects() {
        apiRepository.getAllProjectsData()
    }

    fun getAllTasks() {
        apiRepository.getAllTasksData()
    }

    fun getCurrentUserProfileByEmailViewModel(email: String): MutableLiveData<ApiResponse<*>?>? {
        currentProfileLiveData = MutableLiveData()
        apiRepository.getCurrentUserProfileByEmail(email,object :ApiResponseListener{
            override fun onSuccess(apiResponse: ApiResponse<*>?) {
                currentProfileLiveData?.postValue(apiResponse)

            }

            override fun onFailure(apiResponseFail: ApiResponse<*>?) {
                currentProfileLiveData?.postValue(apiResponseFail)
            }

            override fun onError(apiResponseError: ApiResponse<*>?) {
                currentProfileLiveData?.postValue(apiResponseError)
            }
        })


        return currentProfileLiveData
    }


}