package com.orion.ri.fragments.project

import androidx.lifecycle.ViewModel
import com.orion.ri.model.project.OnGoingProjectData

class ProjectViewModel:ViewModel() {
    val projects = arrayOf(
        OnGoingProjectData(
            "1",
            "Project 1",
            "Client A",
            "This is project 1 description.",
            "2024-05-01"
        ),
        OnGoingProjectData(
            "2",
            "Project 2",
            "Client B",
            "This is project 2 description.",
            "2024-06-15"
        ),
        OnGoingProjectData(
            "3",
            "Project 3",
            "Client C",
            "This is project 3 description.",
            "2024-07-30"
        )
    )

    fun getOngoingProjectsData(): Array<OnGoingProjectData> {
        return projects
    }
}