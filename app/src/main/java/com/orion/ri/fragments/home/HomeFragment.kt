package com.orion.ri.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.orion.ri.R
import com.orion.ri.databinding.FragmentHomeBinding
import com.orion.ri.model.HomeCard

class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    val cardsList = listOf(
        HomeCard("Ongoing Projects", drawable = R.drawable.home_banner_ongoing),
        HomeCard("Ongoing Projects", drawable = R.drawable.home_banner_ongoing),
        HomeCard("Ongoing Projects", drawable = R.drawable.home_banner_ongoing),
    HomeCard("Upcoming Projects", drawable = R.drawable.home_banner_upcoming))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupAdapter()
    }

    private fun setupAdapter() {
        val gridLayoutManager = GridLayoutManager(context,2)
        binding.rvCards.layoutManager = gridLayoutManager

        val adapter = HomeCardsAdapter(context,cardsList)
        binding.rvCards.adapter = adapter

    }

    companion object {


    }
}