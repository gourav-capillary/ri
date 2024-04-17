package com.orion.ri.model.task

import java.io.Serializable

data class TaskItem(
    val name:String,
    val description:String,
    val id:String,
    val deadline:String,
    val progress:Int = 50
): Serializable {
}
