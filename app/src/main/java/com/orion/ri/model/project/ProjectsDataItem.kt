package com.orion.ri.model.project

import java.io.Serializable

data class ProjectsDataItem(
    val projectId: String,
    val projectName: String,
    val clientName: String,
    val description: String,
    val deadline: String,
    val type:String = "ongoing"
):Serializable
