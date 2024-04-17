package com.orion.ri.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.orion.ri.R
import com.orion.ri.activities.login.LoginActivity
import com.orion.ri.databinding.FragmentProfileBinding
import com.orion.ri.model.profile.ProfileData

class ProfileFragment : Fragment() {
    lateinit var binding : FragmentProfileBinding
    lateinit var auth: FirebaseAuth

    val profileItems = listOf(
        ProfileData("Language", R.drawable.ic_profile_language),
        ProfileData("Help",R.drawable.ic_profile_terms_and_condition),
        ProfileData("Contact Us",R.drawable.ic_profile_contact_us),
        ProfileData("Privacy Policy",R.drawable.ic_profile_privacy_policy),
    )

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
        setupAdapter()
        setupListeners()
    }

    private fun setupAdapter() {
        binding.rvProfile.layoutManager = LinearLayoutManager(context,RecyclerView.VERTICAL,false)
        binding.rvProfile.adapter = ProfileAdapter(profileItems,object:ProfileItemClickListener{
            override fun clickProfileItem(profileData: ProfileData) {

            }

        })
    }

    private fun setupListeners() {
        binding.btnLogout.setOnClickListener{
            showConfirmationDialog()
        }
    }

    private fun basicSetup() {
        auth = FirebaseAuth.getInstance()
        binding.toolbar.heading.text = "Profile"
    }

    fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Confirmation")
        builder.setMessage("Are you sure you want to logout?")

        // "Yes" Button
        builder.setPositiveButton("Yes") { dialog, which ->
            Toast.makeText(context, "Logout Successful!", Toast.LENGTH_SHORT).show()
            auth.signOut()
            LoginActivity.launchActivity(requireActivity())
            activity?.finish()
            dialog.dismiss() // Dismiss the dialog
        }

        // "No" Button
        builder.setNegativeButton("No") { dialog, which ->
            Toast.makeText(context, "Cancelled!", Toast.LENGTH_SHORT).show()
            dialog.dismiss() // Dismiss the dialog
        }

        val dialog = builder.create()
        dialog.show()
    }
    companion object {


    }
}