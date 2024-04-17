package com.orion.ri.fragments.project.projectcategory.upcoming

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orion.ri.databinding.FragmentProjectBinding
import com.orion.ri.databinding.FragmentProjectOngoingBinding
import com.orion.ri.databinding.FragmentProjectUpcomingBinding

class UpcomingProjectFragment : Fragment() {
    private lateinit var binding : FragmentProjectUpcomingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProjectUpcomingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
    }


    companion object {


    }
}