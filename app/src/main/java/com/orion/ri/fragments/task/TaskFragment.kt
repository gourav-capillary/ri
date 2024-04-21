package com.orion.ri.fragments.task

import DataStoreHelper
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
import com.orion.ri.model.response.TaskResponse

class TaskFragment : Fragment() {
    private lateinit var binding: FragmentTaskBinding
    var tasksList :MutableList<TaskResponse>? =null



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

    override fun onResume() {
        super.onResume()
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

        val tasksList = DataStoreHelper.getInstance().getAllTasks()
        binding.rvTasks.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvTasks.adapter = TasksAdapter(tasksList,object :TaskClickedListener{
            override fun clickTask(task: TaskResponse) {
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