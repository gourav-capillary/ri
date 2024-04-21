package com.orion.ri.activities.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityAddEmployeeBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.request.EmployeeRequest
import com.orion.ri.viewmodels.EmployeeViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddEmployeeActivity : BaseActivity() {

    private lateinit var binding: ActivityAddEmployeeBinding
    private val employeeViewModel: EmployeeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initViews()
        setupListeners()
    }

    private fun initViews() {
        enableBackButton(binding.toolbar.backButton)
        binding.toolbar.heading.text = "Add Employee"
    }

    private fun setupListeners() {
        binding.edDOB.setOnClickListener {
            showDatePicker()
        }
        binding.btnAddEmployee.setOnClickListener {
            if (validateFields()) {
                createEmployeeAndSave()
            }

        }
    }

    private fun createEmployeeAndSave() {
        val employee = EmployeeRequest(
            binding.edName.text.toString(),
            binding.edDOB.text.toString(),
            binding.edEmail.text.toString(),
            binding.edPassword.text.toString(),
            binding.edAddress.text.toString(),
            binding.edMobileNumber.text.toString(),
            null,
            binding.edDesignation.text.toString(),
            binding.edQualification.text.toString(),
            binding.edExperience.text.toString(),
            "employee"
        )

        employeeViewModel.createEmployee(employee)
        val task = { finish() }
        Utils.scheduleTaskWithCountDownTimer(2000, task)


    }

    private fun validateFields(): Boolean {
        if (binding.edName.text.isNullOrEmpty()) {
            binding.tilName.helperText = "Name cant be empty"
            return false
        }
        if (binding.edDOB.text.isNullOrEmpty()) {
            binding.tilDOB.helperText = "DOB cant be empty"
            return false
        }
        if (binding.edEmail.text.isNullOrEmpty()) {
            binding.tilEmail.helperText = "email cant be empty"
            return false
        }
        if (binding.edPassword.text.isNullOrEmpty()) {
            binding.tilPassword.helperText = "password cant be empty"
            return false
        }
        if (binding.edAddress.text.isNullOrEmpty()) {
            binding.tilAddress.helperText = "Address cant be empty"
            return false
        }
        if (binding.edMobileNumber.text.isNullOrEmpty()) {
            binding.tilMobileNumber.helperText = "MobileNumber cant be empty"
            return false
        }
        if (binding.edDesignation.text.isNullOrEmpty()) {
            binding.tilDesignation.helperText = "Designation cant be empty"
            return false
        }
        if (binding.edExperience.text.isNullOrEmpty()) {
            binding.tilExperience.helperText = "Experience cant be empty"
            return false
        }
        if (binding.edExperience.text.isNullOrEmpty()) {
            binding.tilExperience.helperText = "Experience cant be empty"
            return false
        }
        return true
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -18) // Set the initial date to 18 years ago

        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Select Date Of Birth")
                .setSelection(calendar.timeInMillis).build()

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            binding.edDOB.setText(sdf.format(Date(selectedDate)))
        }

        datePicker.show(supportFragmentManager, "DATE_PICKER")
    }


    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, AddEmployeeActivity::class.java)
            activity.startActivity(intent)
        }
    }

}