package com.orion.ri.fragments.project.projectcategory.ongoing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.orion.ri.activities.project.ProjectDetailsActivity
import com.orion.ri.databinding.FragmentProjectOngoingBinding
import com.orion.ri.fragments.project.ProjectViewModel
import com.orion.ri.model.project.ProjectsDataItem

class OnGoingProjectFragment : Fragment() {
    private lateinit var binding: FragmentProjectOngoingBinding
    val projectViewModel: ProjectViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectOngoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val ongoingProjectsList = projectViewModel.getProjectsList()

        if (ongoingProjectsList.isEmpty()) {
            binding.rvOnGoingProjects.visibility = View.GONE
            binding.emptyProjectScreen.root.visibility = View.VISIBLE
        } else {
            binding.rvOnGoingProjects.visibility = View.VISIBLE
            binding.emptyProjectScreen.root.visibility = View.GONE
        }

        val layoutManager = LinearLayoutManager(context)
        binding.rvOnGoingProjects.layoutManager = layoutManager

        val adapter =
            OnGoingProjectsAdapter(context, ongoingProjectsList, object : ProjectClickedListener {
                override fun clickProject(ongoingProject: ProjectsDataItem) {
                    ProjectDetailsActivity.launchActivity(requireActivity(), ongoingProject)
                }

            })
        binding.rvOnGoingProjects.adapter = adapter

    }


    companion object {


    }
}