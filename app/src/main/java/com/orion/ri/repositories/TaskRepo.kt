package com.orion.ri.repositories

import com.orion.ri.api.APIClient
import com.orion.ri.api.APIRepository
import com.orion.ri.api.APIService
import com.orion.ri.model.request.TaskRequest
import com.orion.ri.model.response.TaskResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TaskRepo {
    fun deleteTask(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.deleteTask(id)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        APIRepository.getAllTasksData()
                    }else{
                        println("ERROR IN TASK"+response.message())
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("ERROR2 FAILURE IN TASK"+t.message)

                }

            })

        }
    }

    fun createTask(task: TaskRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.createTask(task)
            call.enqueue(object :Callback<TaskResponse>{
                override fun onResponse(
                    call: Call<TaskResponse>,
                    response: Response<TaskResponse>
                ) {
                    if (response.isSuccessful){
                        APIRepository.getAllTasksData()
                    }
                }

                override fun onFailure(call: Call<TaskResponse>, t: Throwable) {
                    println("Failure IN CREATE TASK API CALL")
                }

            })
        }

    }

}
