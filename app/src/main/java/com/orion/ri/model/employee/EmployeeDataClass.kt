package com.orion.ri.model.employee

import java.io.Serializable

data class EmployeeDataClass(
    val id: Int,
    val name: String?,
    val dob: String? = null,
    val email: String? = null,
    val address: String? = null,
    val contactNumber: String?,
    val alternateContactNumber: String? = null,
    val designation: String?,
    val qualification: String? = null,
    val experience: String? = null,
    var userType: String? = null
    ):Serializable