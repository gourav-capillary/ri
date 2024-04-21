package com.orion.ri.model.response


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ProjectResponse(
    @SerializedName("clientName")
    val clientName: String,
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("employees")
    val employees: List<String>,
    @SerializedName("_id")
    val id: String,
    @SerializedName("projectName")
    val projectName: String,
    @SerializedName("__v")
    val v: Int
):Serializable