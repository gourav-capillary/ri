package com.orion.ri.activities.project

import DataStoreHelper
import MultiSelectAdapter
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityAddProjectBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.request.ProjectRequest
import com.orion.ri.viewmodels.ProjectViewModel
import com.orion.ri.model.response.EmployeesResponse

class AddProjectActivity : BaseActivity() {

    private lateinit var binding: ActivityAddProjectBinding
    private lateinit var adapter: MultiSelectAdapter
    var items: MutableList<EmployeesResponse> = mutableListOf()
    private val projectViewModel: ProjectViewModel by viewModels()


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

        var emps: List<EmployeesResponse> = listOf()
        emps = DataStoreHelper.getInstance().getAllUsers()
        adapter = MultiSelectAdapter(
            this@AddProjectActivity,
            android.R.layout.simple_dropdown_item_1line,
            emps
        )
        binding.edEmployees.setAdapter(adapter)
        binding.edEmployees.threshold = 0



        binding.rvEmployeesSelected.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
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
        binding.toolbar.heading.text = "Add Project"
    }

    private fun setupListeners() {
        binding.btnAddProject.setOnClickListener {
            if (validateFields()){
                createProjectAndSave()
            }
        }
    }

    private fun createProjectAndSave() {
        val selectedEmployees = adapter.getSelectedItems()

        val stringEmpIDs:MutableList<String> = mutableListOf()
        for (i in selectedEmployees){
            i.id?.let { stringEmpIDs.add(it) }
        }
        val project = ProjectRequest(binding.edName.text.toString(),binding.edDescription.text.toString(),binding.edClientName.text.toString(),binding.edDeadline.text.toString(),stringEmpIDs)
        projectViewModel.createProject(project)

        val task = { finish() }
        Utils.scheduleTaskWithCountDownTimer(2000,task)
    }

    private fun validateFields(): Boolean {
        if (binding.edName.text?.isNullOrEmpty() == true){
            binding.tilName.helperText = "Name cant be empty"
            return false
        }
        if (binding.edDescription.text?.isNullOrEmpty() == true){
            binding.tilDescription.helperText = "description cant be empty"
            return false
        }
        if (binding.edClientName.text?.isNullOrEmpty() == true){
            binding.tilClientName.helperText = "clientname cant be empty"
            return false
        }
        if (binding.edDeadline.text?.isNullOrEmpty() == true){
            binding.tilDeadline.helperText = "deadline cant be empty"
            return false
        }
        if (adapter.getSelectedItems().size == 0){
            binding.tilEmployees.helperText = "Select emplyees to assign this project"
            return false
        }
        return true
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