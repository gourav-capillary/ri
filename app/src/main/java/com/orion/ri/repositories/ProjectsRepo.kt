package com.orion.ri.repositories

import com.orion.ri.api.APIClient
import com.orion.ri.api.APIRepository
import com.orion.ri.api.APIService
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.model.response.ProjectResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProjectsRepo {

    fun deleteProject(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.deleteProject(id)
            call.enqueue(object :Callback<Void>{
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful){
                        val data = response.body()
                        APIRepository.getAllProjectsData()

                    }else{
                        println("ERROR")
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println("ERROR2 FAILURE"+t.message)
                }

            })

        }

    }

    fun createProject(project: ProjectRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.createProject(project)
            call.enqueue(object:Callback<ProjectResponse>{
                override fun onResponse(
                    call: Call<ProjectResponse>,
                    response: Response<ProjectResponse>
                ) {
                    if (response.isSuccessful){
                        APIRepository.getAllProjectsData()
                    }
                }

                override fun onFailure(call: Call<ProjectResponse>, t: Throwable) {
                    println("Failure IN CREATE Project API CALL")
                }

            })
        }

    }
}