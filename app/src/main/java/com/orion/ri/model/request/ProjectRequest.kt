package com.orion.ri.model.request

class ProjectRequest (
    val projectName: String,
    val clientName: String,
    val description: String,
    val deadline: String,
    val employees: List<String>
)
