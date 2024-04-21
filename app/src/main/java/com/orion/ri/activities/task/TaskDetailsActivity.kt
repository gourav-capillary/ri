package com.orion.ri.activities.task

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityTaskDetailsBinding
import com.orion.ri.helper.Utils
import com.orion.ri.model.response.TaskResponse
import com.orion.ri.model.task.TaskItem
import com.orion.ri.viewmodels.TaskViewModel

class TaskDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityTaskDetailsBinding
    lateinit var taskItem: TaskResponse
    val taskViewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        getIntentData()
        basicSetup()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnDeleteTask.setOnClickListener {
            showConfirmationDialog()
        }
    }


    fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to delete?")

        builder.setPositiveButton("Yes") { dialog, which ->

            Toast.makeText(this@TaskDetailsActivity, "Confirmed!", Toast.LENGTH_SHORT).show()
            taskViewModel.deleteProject(taskItem.id)
            dialog.dismiss() // Dismiss the dialog
            val task = { finish() }
            Utils.scheduleTaskWithCountDownTimer(2000, task)
        }

        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(this@TaskDetailsActivity, "Cancelled!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Dismiss the dialog
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun getIntentData() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            taskItem = intent.getSerializableExtra("TASK_ITEM", TaskResponse::class.java)!!
        } else {
            taskItem = intent.getSerializableExtra("TASK_ITEM") as TaskResponse
        }
    }

    private fun basicSetup() {

        println(taskItem)
        binding.taskName.text = taskItem.name
    }

    companion object {
        fun launchActivity(activity: Activity, taskItem: TaskResponse) {
            val intent = Intent(activity, TaskDetailsActivity::class.java)
            intent.putExtra("TASK_ITEM", taskItem)
            activity.startActivity(intent)
        }
    }
}