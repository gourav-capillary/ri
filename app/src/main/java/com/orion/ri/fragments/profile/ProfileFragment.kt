package com.orion.ri.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.orion.ri.activities.login.LoginActivity
import com.orion.ri.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding
    lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        basicSetup()
        setupListeners()
    }

    private fun setupListeners() {
        binding.btnLogout.setOnClickListener{
            auth.signOut()
            LoginActivity.launchActivity(requireActivity())
            activity?.finish()
        }
    }

    private fun basicSetup() {
        auth = FirebaseAuth.getInstance()
    }

    companion object {


    }
}