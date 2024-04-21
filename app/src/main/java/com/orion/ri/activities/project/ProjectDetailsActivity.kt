package com.orion.ri.activities.project

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityProjectDetailsBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.project.ProjectsDataItem
import com.orion.ri.model.response.ProjectResponse
import com.orion.ri.viewmodels.ProjectViewModel

class ProjectDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityProjectDetailsBinding
    val projectViewModel: ProjectViewModel by viewModels()
    lateinit var projectItem: ProjectResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        getIntentData()
        basicSetup()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnDeleteProject.setOnClickListener{
            showConfirmationDialog()
        }
    }

    fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete?")

        builder.setPositiveButton("Yes") { dialog, which ->

            Toast.makeText(this@ProjectDetailsActivity, "Confirmed!", Toast.LENGTH_SHORT).show()
            projectViewModel.deleteProject(projectItem.id)
            dialog.dismiss() // Dismiss the dialog

            val task = { finish() }
            Utils.scheduleTaskWithCountDownTimer(2000,task)

        }

        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this@ProjectDetailsActivity, "Cancelled!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Dismiss the dialog
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun getIntentData() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            projectItem = intent.getSerializableExtra(
                "ONGOING_PROJECT_DETAILS",
                ProjectResponse::class.java
            )!!
        } else {
            projectItem =
                intent.getSerializableExtra("ONGOING_PROJECT_DETAILS") as ProjectResponse
        }
    }

    private fun basicSetup() {

        println(projectItem)
        binding.projectName.text = projectItem.projectName
    }

    companion object {
        fun launchActivity(activity: Activity, ongoingProject: ProjectResponse) {
            val intent = Intent(activity, ProjectDetailsActivity::class.java)
            intent.putExtra("ONGOING_PROJECT_DETAILS", ongoingProject)
            activity.startActivity(intent)
        }
    }
}