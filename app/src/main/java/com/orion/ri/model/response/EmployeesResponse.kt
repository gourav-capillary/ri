package com.orion.ri.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class EmployeesResponse(
    @SerializedName("address")
    val address: String?,
    @SerializedName("alternateContactNumber")
    val alternateContactNumber: String?,
    @SerializedName("contactNumber")
    val contactNumber: String?,
    @SerializedName("designation")
    val designation: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("experience")
    val experience: String,
    @SerializedName("_id")
    val id: String? = null,
    @SerializedName("name")
    val name: String?,
    @SerializedName("picture")
    val picture: String?,
    @SerializedName("projects")
    val projects: List<String?>? = null,
    @SerializedName("qualification")
    val qualification: String? = null,
    @SerializedName("tasks")
    val tasks: List<Any>? = null,
    @SerializedName("userType")
    var userType: String?,
    @SerializedName("__v")
    val v: Int = 0
):Serializable