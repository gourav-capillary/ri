package com.orion.ri.activities.employee

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityEmployeeDetailsBinding
import com.orion.ri.model.employee.EmployeeDataClass

class EmployeeDetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityEmployeeDetailsBinding
    lateinit var employeeObject: EmployeeDataClass
    private val CALL_PERMISSION_REQUEST_CODE = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEmployeeDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()


    }

    private fun initialize() {
        getIntentData()
        initViews()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnDelete.setOnClickListener {
            showConfirmationDialog()
        }

        binding.btnCall.setOnClickListener {
            val phoneNumber =
                employeeObject.contactNumber// Replace with the phone number you want to dial

            // Check if permission to call is granted
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // Permission is not granted, request the permission
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CALL_PHONE), CALL_PERMISSION_REQUEST_CODE
                )
            } else {
                // Permission is granted, proceed with the call
                if (phoneNumber != null) {
                    makePhoneCall(phoneNumber)
                }
            }
        }
    }

    private fun showPopupForDelete() {

    }


    private fun makePhoneCall(phoneNumber: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumber")

        // Ensure the device can handle this intent
        if (callIntent.resolveActivity(packageManager) != null) {
            startActivity(callIntent)
        }
    }

    // Handle the permission request result
    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        if (requestCode == CALL_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the call
                val phoneNumber = "1234567890" // Replace with the phone number you want to dial
                makePhoneCall(phoneNumber)
            } else {
                // Permission denied, show a message or handle as needed
            }
        }
    }

    fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete?")

        // "Yes" Button
        builder.setPositiveButton("Yes") { dialog, which ->
            println("POSITIVE WORKING")
            Toast.makeText(this@EmployeeDetailsActivity, "Confirmed!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Dismiss the dialog
        }

        // "No" Button
        builder.setNegativeButton("No") { dialog, which ->
            println("NEGATIVE WORKING")

            Toast.makeText(this@EmployeeDetailsActivity, "Cancelled!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Dismiss the dialog
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun getIntentData() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            employeeObject = intent.getSerializableExtra(
                "EMPLOYEE_DETAILS", EmployeeDataClass::class.java
            )!!
        } else {
            employeeObject = intent.getSerializableExtra("EMPLOYEE_DETAILS") as EmployeeDataClass
        }
    }

    private fun initViews() {
        enableBackButton(binding.toolbar.backButton)
        binding.toolbar.heading.text = "Employee Details"

        binding.empName.text = employeeObject.name
        binding.empDesignation.text = employeeObject.designation
    }

    companion object {
        fun launchActivity(activity: Activity, employee: EmployeeDataClass) {
            val intent = Intent(activity, EmployeeDetailsActivity::class.java)
            intent.putExtra("EMPLOYEE_DETAILS", employee)
            activity.startActivity(intent)
        }
    }


}