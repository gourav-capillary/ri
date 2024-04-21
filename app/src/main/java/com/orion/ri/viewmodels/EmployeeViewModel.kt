package com.orion.ri.viewmodels

import com.orion.ri.model.request.EmployeeRequest
import com.orion.ri.repositories.EmployeesRepo

class EmployeeViewModel: CommonViewModel() {
    val employeesRepo = EmployeesRepo()

    fun createEmployee(employee: EmployeeRequest) {
        employeesRepo.createEmployee(employee)

    }

    fun deleteEmployee(id: String?) {
        if (id != null) {
            employeesRepo.deleteEmployee(id)
        }

    }


}