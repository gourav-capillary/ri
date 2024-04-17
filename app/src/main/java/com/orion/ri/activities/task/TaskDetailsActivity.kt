package com.orion.ri.activities.task

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityTaskDetailsBinding
import com.orion.ri.model.task.TaskItem

class TaskDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityTaskDetailsBinding
    lateinit var taskItem:TaskItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        getIntentData()
        basicSetup()
    }

    private fun getIntentData() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            taskItem = intent.getSerializableExtra("TASK_ITEM",TaskItem::class.java)!!
        } else {
            taskItem = intent.getSerializableExtra("TASK_ITEM") as TaskItem
        }
    }

    private fun basicSetup() {

        println(taskItem)
        binding.taskName.text = taskItem.name
    }

    companion object{
        fun launchActivity(activity: Activity, taskItem: TaskItem){
            val intent = Intent(activity, TaskDetailsActivity::class.java)
            intent.putExtra("TASK_ITEM",taskItem)
            activity.startActivity(intent)
        }
    }
}