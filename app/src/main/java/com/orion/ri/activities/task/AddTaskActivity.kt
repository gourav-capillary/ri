package com.orion.ri.activities.task

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityAddEmployeeBinding
import com.orion.ri.databinding.ActivityAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTaskActivity : BaseActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initViews()
        setupListeners()
    }

    private fun initViews() {
        enableBackButton(binding.toolbar.backButton)
        binding.toolbar.heading.text = "Add Task"
    }

    private fun setupListeners() {

    }

    private fun showDatePicker() {

    }


    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, AddTaskActivity::class.java)
            activity.startActivity(intent)
        }
    }

}