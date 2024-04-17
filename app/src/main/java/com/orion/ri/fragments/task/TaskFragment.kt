package com.orion.ri.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.activities.task.AddTaskActivity
import com.orion.ri.activities.task.TaskDetailsActivity
import com.orion.ri.databinding.FragmentTaskBinding
import com.orion.ri.helper.AppConstants
import com.orion.ri.model.task.TaskItem

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding

    val tasksList = listOf(
        TaskItem(
            "Complete Report",
            "Write a detailed report on the project progress.",
            "id001",
            "2024-04-10",
        ), TaskItem(
            "Prepare Presentation",
            "Create slides for the upcoming meeting.",
            "id002",
            "2024-04-15",
        ), TaskItem(
            "Review Code Changes",
            "Review and merge recent code changes.",
            "id003",
            "2024-04-20",
        ), TaskItem(
            "Bug Fixes",
            "Address reported bugs and fix issues.",
            "id004",
            "2024-04-25",
        ), TaskItem(
            "Jhadu Pocha",
            "Saaf safai karo bhai",
            "id005",
            "2024-04-25",
        )
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        basicSetup()
        setViewByUserType()
        setupAdapter()
        setListeners()
    }

    private fun setListeners() {
        binding.toolbar.add.setOnClickListener {
            AddTaskActivity.launchActivity(requireActivity())
        }
    }

    private fun setViewByUserType() {
        val userType = DataStoreHelper.getInstance().getCurrentUserType()
        if (userType == AppConstants.EMPLOYEE){
            binding.toolbar.add.visibility = View.GONE
        }
    }
    private fun setupAdapter() {
        binding.rvTasks.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvTasks.adapter = TasksAdapter(tasksList,object :TaskClickedListener{
            override fun clickTask(task: TaskItem) {
                TaskDetailsActivity.launchActivity(requireActivity(),task)
            }

        })
    }

    private fun basicSetup() {
        binding.toolbar.heading.text = "Tasks"
        binding.toolbar.add.visibility = View.VISIBLE
    }

    companion object {


    }
}