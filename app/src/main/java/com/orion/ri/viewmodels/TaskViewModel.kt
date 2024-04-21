package com.orion.ri.viewmodels

import androidx.lifecycle.ViewModel
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.model.request.TaskRequest
import com.orion.ri.repositories.ProjectsRepo
import com.orion.ri.repositories.TaskRepo

class TaskViewModel:ViewModel(){
    private val taskRepo = TaskRepo()
    fun deleteProject(id: String) {
        taskRepo.deleteTask(id)
    }

    fun createProject(task: TaskRequest) {
        taskRepo.createTask(task)
    }

}
