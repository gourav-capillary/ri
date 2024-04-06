package com.orion.ri.fragments.task

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.orion.ri.R
import com.orion.ri.databinding.FragmentHomeBinding
import com.orion.ri.databinding.FragmentTaskBinding

class TaskFragment : Fragment() {
    private lateinit var binding : FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater,container,false)
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