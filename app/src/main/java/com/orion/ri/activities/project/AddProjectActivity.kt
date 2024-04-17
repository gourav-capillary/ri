package com.orion.ri.activities.project

import DataStoreHelper
import MultiSelectAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityAddEmployeeBinding
import com.orion.ri.databinding.ActivityAddProjectBinding
import com.orion.ri.databinding.ActivityAddTaskBinding
import com.orion.ri.model.employee.EmployeeDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddProjectActivity : BaseActivity() {

    private lateinit var binding: ActivityAddProjectBinding
    private lateinit var adapter: MultiSelectAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddProjectBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initViews()
        setupListeners()
        setupDropDown()
    }

    private fun setupDropDown() {
        val items = listOf("Item 1", "Item 2", "Item 3", "Item 4", "Item 5")

        var emps:List<EmployeeDataClass> = listOf()
        CoroutineScope(Dispatchers.Main).launch {
            emps = DataStoreHelper.getInstance().getAllUsers()
            adapter = MultiSelectAdapter(this@AddProjectActivity, android.R.layout.simple_dropdown_item_1line, items)

            binding.edEmployees.setAdapter(adapter)
            binding.edEmployees.threshold = 0

        }


        binding.edEmployees.setOnItemClickListener { _, _, position, _ ->
            adapter.toggleSelection(position)
//            binding.edEmployees.setText(adapter.getSelectedItems().toString())
        }

        val selectedItems = adapter.getSelectedItems()
        println("APDJOPASJD"+selectedItems)

    }

    private fun initViews() {
        enableBackButton(binding.toolbar.backButton)
        binding.toolbar.heading.text = "Add Project"
    }

    private fun setupListeners() {
        binding.btnAddProject.setOnClickListener{
            println("oasdhsajdj"+adapter.getSelectedItems())
        }
    }

    private fun showDatePicker() {

    }


    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, AddProjectActivity::class.java)
            activity.startActivity(intent)
        }
    }

}