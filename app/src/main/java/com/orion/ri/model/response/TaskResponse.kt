package com.orion.ri.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class TaskResponse(
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("employees")
    val employees: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("__v")
    val v: Int
):Serializable