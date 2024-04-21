package com.orion.ri.model.request

data class EmployeeRequest(
    val name: String?,
    val dob: String?,
    val email: String?,
    val password: String?,
    val address: String?,
    val contactNumber: String?,
    val alternateContactNumber: String?,
    val designation: String?,
    val qualification: String?,
    val experience: String?,
    val userType: String?
) {

}
