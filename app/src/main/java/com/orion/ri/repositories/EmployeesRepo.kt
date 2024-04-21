package com.orion.ri.repositories

import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.auth
import com.orion.ri.api.APIClient
import com.orion.ri.api.APIRepository
import com.orion.ri.api.APIService
import com.orion.ri.model.request.EmployeeRequest
import com.orion.ri.model.response.EmployeesResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmployeesRepo {

    val auth: FirebaseAuth by lazy { Firebase.auth }

    fun createEmployee(employee: EmployeeRequest) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.createEmployee(employee)
            call.enqueue(object : Callback<EmployeesResponse> {
                override fun onResponse(
                    call: Call<EmployeesResponse>, response: Response<EmployeesResponse>
                ) {
                    if (response.isSuccessful) {
//                        employee.email?.let { employee.password?.let { pswd ->
//                            createUserInFirebase(it,
//                                pswd
//                            )
//                        } }
                        APIRepository.getAllEmployeesData()
                    }
                }

                override fun onFailure(call: Call<EmployeesResponse>, t: Throwable) {
                    println("Failure IN CREATE EMP API CALL")
                }

            })
        }


    }

    private fun createUserInFirebase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {task->
            if(task.isSuccessful){
            }else{
                val exception = task.exception
                if (exception is FirebaseAuthException) {
                    val errorCode = exception.errorCode
                    val errorMessage = exception.message
                    println("EMESALDSAD "+errorMessage)
                    // Handle specific error codes or show error message
                }
                println("USER CREATED NOT")
            }

        }

    }

    fun deleteEmployee(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val apiService = APIClient.getApiService(APIService::class.java)

            val call = apiService.deleteEmployeeById(id)
            call.enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        APIRepository.getAllEmployeesData()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    println(t.message)
                }

            })
        }
    }
}