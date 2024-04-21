package com.orion.ri.model.request


import com.google.gson.annotations.SerializedName

data class TaskRequest(
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("employees")
    val employees: List<String>,
    @SerializedName("name")
    val name: String
)