package com.orion.ri.activities.dashboard

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.orion.ri.R
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.activities.login.LoginActivity
import com.orion.ri.databinding.ActivityDashboardBinding
import com.orion.ri.fragments.home.HomeFragment
import com.orion.ri.fragments.profile.ProfileFragment
import com.orion.ri.fragments.project.ProjectFragment
import com.orion.ri.fragments.task.EmployeeFragment
import com.orion.ri.fragments.task.TaskFragment

class DashBoardActivity: BaseActivity() {
    lateinit var binding: ActivityDashboardBinding
    val auth:FirebaseAuth by lazy { Firebase.auth }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user == null){
            LoginActivity.launchActivity(this)
            finish()
        }
    }
    fun init() {
        basicSetup()
        initBottomNavBar()
        setupListeners()
    }

    private fun initBottomNavBar() {
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener {item->
//            binding.headerUserDetails.root.visibility = View.GONE
            when(item.itemId){
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
//                    binding.headerUserDetails.root.visibility = View.VISIBLE
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_project -> {
                    loadFragment(ProjectFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_task -> {
                    loadFragment(TaskFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_employee -> {
                    loadFragment(EmployeeFragment())
                    return@setOnItemSelectedListener true
                }
                else -> return@setOnItemSelectedListener false
            }

            true
        }
    }

    private fun basicSetup() {

//        binding.bottomNavigation.menu.findItem(R.id.navigation_employee).isVisible =false
//        binding.headerUserDetails.root.visibility = View.VISIBLE

        if (actionBar != null){
            actionBar?.hide()
        }
    }

    private fun setupListeners() {
//        binding.btnLogout.setOnClickListener {
//            auth.signOut()
//            finish()
//        }
    }


    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }


    companion object{
        fun launchActivity(activity: Activity){
            val intent = Intent(activity, DashBoardActivity::class.java)
            activity.startActivity(intent)
        }
    }
}