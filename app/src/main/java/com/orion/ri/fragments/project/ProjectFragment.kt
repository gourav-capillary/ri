package com.orion.ri.fragments.project

import DataStoreHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.orion.ri.activities.project.AddProjectActivity
import com.orion.ri.databinding.FragmentProjectBinding
import com.orion.ri.helper.AppConstants
import kotlinx.coroutines.runBlocking

class ProjectFragment : Fragment() {
    private lateinit var binding : FragmentProjectBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initViews()
        setViewByUserType()
        setupTabLayout()
        setupListeners()
    }

    private fun setViewByUserType() {
        runBlocking {
            val list = DataStoreHelper.getInstance().getProjects()
            println("jasdhiashdihsadioh"+list)
        }
        val userType = DataStoreHelper.getInstance().getCurrentUserType()
        if (userType == AppConstants.EMPLOYEE){
            binding.toolbar.add.visibility = View.GONE
        }
    }

    private fun setupListeners() {
        binding.toolbar.add.setOnClickListener {
            AddProjectActivity.launchActivity(requireActivity())
        }
    }

    private fun initViews() {
        binding.toolbar.heading.text = "Projects"
        binding.toolbar.add.visibility = View.VISIBLE
    }

    private fun setupTabLayout() {


        binding.viewPager.adapter = ProjectPagerAdapter(requireActivity())

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Ongoing"
                1 -> tab.text = "Upcoming"
            }
        }.attach()

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.let {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Not needed
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Not needed
            }
        })
    }

    companion object {


    }
}