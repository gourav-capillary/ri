package com.orion.ri.activities.task

import MultiSelectAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.datepicker.MaterialDatePicker
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.activities.project.SelectedEmployeesAdapter
import com.orion.ri.databinding.ActivityAddEmployeeBinding
import com.orion.ri.databinding.ActivityAddTaskBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.model.request.TaskRequest
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.model.response.TaskResponse
import com.orion.ri.viewmodels.ProjectViewModel
import com.orion.ri.viewmodels.TaskViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class AddTaskActivity : BaseActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var adapter: MultiSelectAdapter
    var items: MutableList<EmployeesResponse> = mutableListOf()
    private val taskViewModel: TaskViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        initViews()
        setupAdapter()
        setupListeners()
    }

    private fun setupAdapter() {
        var emps: List<EmployeesResponse> = listOf()
        emps = DataStoreHelper.getInstance().getAllUsers()
        adapter = MultiSelectAdapter(
            this@AddTaskActivity,
            android.R.layout.simple_dropdown_item_1line,
            emps
        )
        binding.edEmployees.setAdapter(adapter)
        binding.edEmployees.threshold = 0




        binding.rvEmployeesSelected.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val selectedEmployeesAdapter = SelectedEmployeesAdapter(items)
        binding.rvEmployeesSelected.adapter = selectedEmployeesAdapter

        binding.edEmployees.setOnItemClickListener { _, _, position, _ ->
            adapter.toggleSelection(position)
        }

        binding.edEmployees.setOnDismissListener {
            binding.edEmployees.setText("")
            selectedEmployeesAdapter.updateArray(adapter.getSelectedItems())
        }
    }

    private fun initViews() {
        enableBackButton(binding.toolbar.backButton)
        binding.toolbar.heading.text = "Add Task"
    }

    private fun setupListeners() {

        binding.edDeadline.setOnClickListener {
            showDatePicker()
        }

        binding.btnAddTask.setOnClickListener {
            createTaskAndSave()
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -18) // Set the initial date to 18 years ago

        val datePicker =
            MaterialDatePicker.Builder.datePicker().setTitleText("Select Date Of Birth")
                .setSelection(calendar.timeInMillis).build()

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            binding.edDeadline.setText(sdf.format(Date(selectedDate)))
        }

        datePicker.show(supportFragmentManager, "DATE_PICKER")
    }

    private fun createTaskAndSave() {
        val selectedEmployees = adapter.getSelectedItems()

        val stringEmpIDs: MutableList<String> = mutableListOf()
        for (i in selectedEmployees) {
            i.id?.let { stringEmpIDs.add(it) }
        }
        val task = TaskRequest(
            name = binding.edName.text.toString(),
            description = binding.edDescription.text.toString(),
            deadline = binding.edDeadline.text.toString(),
            employees = stringEmpIDs
        )
        taskViewModel.createProject(task)

        val performThis = { finish() }
        Utils.scheduleTaskWithCountDownTimer(2000, performThis)
    }

    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, AddTaskActivity::class.java)
            activity.startActivity(intent)
        }
    }

}