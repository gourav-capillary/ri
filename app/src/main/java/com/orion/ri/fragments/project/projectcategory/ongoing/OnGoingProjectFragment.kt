package com.orion.ri.fragments.project.projectcategory.ongoing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.orion.ri.activities.ProjectDetailsActivity
import com.orion.ri.databinding.FragmentProjectOngoingBinding
import com.orion.ri.fragments.project.ProjectViewModel
import com.orion.ri.model.project.OnGoingProjectData

class OnGoingProjectFragment : Fragment() {
    private lateinit var binding : FragmentProjectOngoingBinding
    val projectViewModel : ProjectViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectOngoingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val ongoingProjectsList= projectViewModel.getOngoingProjectsData()

        val layoutManager = LinearLayoutManager(context)
        binding.rvOnGoingProjects.layoutManager = layoutManager

        val adapter = OnGoingProjectsAdapter(context,ongoingProjectsList,object : ProjectClickedListener{
            override fun clickProject(ongoingProject: OnGoingProjectData) {
                ProjectDetailsActivity.launchActivity(requireActivity(),ongoingProject)
            }

        })
        binding.rvOnGoingProjects.adapter = adapter

    }


    companion object {


    }
}