package com.orion.ri.fragments.project

import androidx.lifecycle.ViewModel
import com.orion.ri.model.employee.EmployeeDataClass
import com.orion.ri.model.project.ProjectsDataItem

class ProjectViewModel:ViewModel() {
    val projects:List<ProjectsDataItem> = listOf(
        ProjectsDataItem(
            "1",
            "Software Development Project",
            "Client A",
            "This project involves developing a new software application for the client.",
            "2024-05-01"
        ),
        ProjectsDataItem(
            "2",
            "Marketing Campaign",
            "Client B",
            "This project focuses on creating and executing a marketing campaign.",
            "2024-06-15"
        ),
        ProjectsDataItem(
            "3",
            "E-commerce Website Redesign",
            "Client C",
            "Redesigning the client's e-commerce website for better user experience.",
            "2024-07-30"
        ),
        ProjectsDataItem(
            "4",
            "Mobile App Development",
            "Client D",
            "Developing a mobile application for the client's business needs.",
            "2024-08-20"
        ),
        ProjectsDataItem(
            "5",
            "Social Media Strategy",
            "Client E",
            "Creating a comprehensive strategy for the client's social media presence.",
            "2024-09-10"
        ),
        ProjectsDataItem(
            "6",
            "Product Launch Campaign",
            "Client F",
            "Planning and executing a campaign for the launch of a new product.",
            "2024-10-25"
        ),
        ProjectsDataItem(
            "7",
            "Website Content Management",
            "Client G",
            "Managing and updating the content of the client's website.",
            "2024-11-15"
        ),
        ProjectsDataItem(
            "8",
            "Graphic Design Project",
            "Client H",
            "Creating various graphic designs for the client's promotional materials.",
            "2024-12-05"
        ),
        ProjectsDataItem(
            "9",
            "SEO Optimization",
            "Client I",
            "Improving the search engine optimization for the client's website.",
            "2025-01-20"
        ),
        ProjectsDataItem(
            "10",
            "Business Consultation",
            "Client J",
            "Providing strategic consultation to the client for business growth.",
            "2025-02-15"
        )
    )



    private val employee1 = EmployeeDataClass(
        id = 1,
        name = "John Doe",
        dob = "1990-01-01",
        email = "john.doe@example.com",
        address = "123 Street, City",
        contactNumber = "123-456-7890",
        designation = "Software Engineer",
        qualification = "Bachelor's Degree",
        experience = "5 years",
        userType = "admin"
    )

    // Employee 2
    private val employee2 = EmployeeDataClass(
        id = 2,
        name = "Jane Smith",
        dob = "1988-03-15",
        email = "jane.smith@example.com",
        address = "456 Avenue, Town",
        contactNumber = "987-654-3210",
        designation = "Product Manager",
        qualification = "Master's Degree",
        experience = "8 years",
        userType = "employee"
    )

    // Employee 3
    private val employee3 = EmployeeDataClass(
        id = 3,
        name = "Michael Johnson",
        dob = "1995-07-20",
        email = "michael.johnson@example.com",
        address = "789 Road, Village",
        contactNumber = "555-555-5555",
        designation = "UX Designer",
        qualification = "Bachelor's Degree",
        experience = "3 years",
        userType = "employee"
    )

    val employees = listOf(employee1,employee2,employee3)

    fun getProjectsList(): List<ProjectsDataItem> {
        return projects
    }

    fun getEmployeesList(): List<EmployeeDataClass> {
        return employees
    }
}