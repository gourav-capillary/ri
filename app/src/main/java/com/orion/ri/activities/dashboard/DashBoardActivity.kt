package com.orion.ri.activities.dashboard

import DataStoreHelper
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
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
import com.orion.ri.fragments.project.ProjectViewModel
import com.orion.ri.fragments.task.EmployeeFragment
import com.orion.ri.fragments.task.TaskFragment
import com.orion.ri.model.employee.EmployeeDataClass

class DashBoardActivity : BaseActivity() {
    lateinit var binding: ActivityDashboardBinding
    val auth: FirebaseAuth by lazy { Firebase.auth }
    val projectViewModel: ProjectViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    override fun onStart() {
        super.onStart()
        val user = auth.currentUser
        if (user == null) {
            LoginActivity.launchActivity(this)
            finish()
        }
    }

    fun init() {
        getDataFromApis()
        basicSetup()
        initBottomNavBar()
        setupListeners()
    }

    private fun getDataFromApis() {
        getAllUsers()
        getUserDetails()
        getProjectDetails()
    }

    private fun getAllUsers() {
        val employees = projectViewModel.getEmployeesList()
        DataStoreHelper.getInstance().saveAllUsers(employees)
    }

    private fun getUserDetails() {
        val email = auth.currentUser?.email
        val user = EmployeeDataClass(
            id = 1,
            name = "John Doe",
            dob = "1990-01-01",
            email = "john.doe@example.com",
            address = "123 Street, City",
            contactNumber = "123-456-7890",
            designation = "Software Engineer",
            qualification = "Bachelor's Degree",
            experience = "5 years",
            userType = "admin"
        )
        if (user.userType.isNullOrEmpty()) {
            user.userType = "employee"
        }
        DataStoreHelper.getInstance().saveCurrentEmployeeProfile(user)
        DataStoreHelper.getInstance().saveCurrentUserType(user.userType!!)

        if (user.userType == "admin") {
            setupUI(isAdmin = true)
        } else {
            setupUI(isAdmin = false)
        }
        //call api here

    }

    private fun getProjectDetails() {
        val projects = projectViewModel.getProjectsList()
        DataStoreHelper.getInstance().saveProjects(projects)

    }

    private fun setupUI(isAdmin: Boolean) {
        if (!isAdmin) {
            binding.bottomNavigation.menu.removeItem(R.id.navigation_employee)
        }
    }

    private fun initBottomNavBar() {
        loadFragment(HomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
//            binding.headerUserDetails.root.visibility = View.GONE
            when (item.itemId) {
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

        if (actionBar != null) {
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


    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, DashBoardActivity::class.java)
            activity.startActivity(intent)
        }
    }
}