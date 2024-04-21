package com.orion.ri.api

import com.orion.ri.model.request.EmployeeRequest
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.model.response.ProjectResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface APIService {
    @GET(APIEndPoints.EMPLOYEES)
    fun getAllEmployees():Call<List<EmployeesResponse>>

    @POST(APIEndPoints.EMPLOYEES)
    fun createEmployee(@Body employee: EmployeeRequest): Call<EmployeesResponse>

    @DELETE(APIEndPoints.EMPLOYEE_DELETE)
    fun deleteEmployeeById(@Path("id") id: String): Call<Void>


    //projects
    @GET(APIEndPoints.PROJECTS)
    fun getAllProjects():Call<List<ProjectResponse>>

    @DELETE(APIEndPoints.PROJECT_DELETE)
    fun deleteProject(@Path("id") id: String): Call<Void>

    @POST(APIEndPoints.PROJECTS)
    fun createProject(@Body employee: ProjectRequest): Call<ProjectResponse>

    @GET(APIEndPoints.EMPLOYEES_BY_EMAIL)
    fun getUserByEmail(@Path("email") email:String):Call<EmployeesResponse>

}


