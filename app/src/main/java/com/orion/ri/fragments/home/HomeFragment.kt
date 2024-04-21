package com.orion.ri.fragments.home

import DataStoreHelper
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.ri.R
import com.orion.ri.databinding.FragmentHomeBinding
import com.orion.ri.model.home.HomeBanner
import com.orion.ri.model.home.HomeCard
import com.orion.ri.viewmodels.TaskViewModel

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    val taskViewModel: TaskViewModel by viewModels()

    val cardsList = listOf(
        HomeCard(
            "Ongoing Projects",
            drawable = R.drawable.home_banner_ongoing,
            backgroundColor = "#FFAB9B"
        ), HomeCard(
            "Ongoing Projects",
            drawable = R.drawable.home_banner_ongoing,
            backgroundColor = "#FFDA55"
        ), HomeCard(
            "Upcoming Projects",
            drawable = R.drawable.home_banner_upcoming,
            backgroundColor = "#BCC3FF"
        )
    )

    val bannerList = listOf(
        HomeBanner("Discover New Horizons", "Explore amazing destinations"),
        HomeBanner("Find Your Adventure", "Start your journey today"),
        HomeBanner("Escape to Paradise", "Relax and unwind in luxury")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupUI()
        setupAdapter()
        setupViewModelObservers()
    }

    private fun setupViewModelObservers() {
        val currentUser = DataStoreHelper.getInstance().getCurrentUserProfile()
        binding.empName.text = currentUser.name
        binding.empDesignation.text = currentUser.designation
    }

    private fun setupUI() {
    }

    private fun setupAdapter() {
        //cards adapter
        val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.rvCards.layoutManager = layoutManager

        val cardsAdapter = HomeCardsAdapter(cardsList, object : HomeCardClickListener {
            override fun onHomeCardClicked() {

            }

        })
        binding.rvCards.adapter = cardsAdapter


        //banner adapter
        var items = DataStoreHelper.getInstance().getAllTasks()
        if (items.size >3){
            items = items.subList(0,3);
        }

        if (items.isNullOrEmpty()){
            binding.labelTasks.visibility = View.GONE
        }else{
            binding.labelTasks.visibility = View.VISIBLE
        }

        val bannerLayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.rvBanner.layoutManager = bannerLayoutManager

        val bannerAdapter = HomeBannerAdapter(requireActivity(),items)
        binding.rvBanner.adapter = bannerAdapter

    }

    companion object {


    }
}