package com.orion.ri.viewmodels

import androidx.lifecycle.ViewModel
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.repositories.ProjectsRepo

class ProjectViewModel:ViewModel() {
    private val projectRepo = ProjectsRepo()
    fun deleteProject(id: String) {
        projectRepo.deleteProject(id)
    }

    fun createProject(project: ProjectRequest) {
        projectRepo.createProject(project)

    }

}