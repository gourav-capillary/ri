package com.orion.ri.activities.employee

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityAddEmployeeBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddEmployeeActivity : BaseActivity() {

    private lateinit var binding: ActivityAddEmployeeBinding

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
        enableBackButton(binding.addEmpToolbar.backButton)
        binding.addEmpToolbar.heading.text = "Add Employee"
    }

    private fun setupListeners() {
        binding.edDOB.setOnClickListener {
            showDatePicker()
        }
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