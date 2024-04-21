package com.orion.ri.activities.splash

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.orion.ri.R
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.activities.dashboard.DashBoardActivity
import com.orion.ri.helper.Utils
import com.orion.ri.model.response.EmployeesResponse
import com.orion.ri.viewmodels.CommonViewModel
import java.util.Timer
import java.util.TimerTask


class StartPageActivity : BaseActivity() {
    val commonViewModel: CommonViewModel by viewModels()
    val auth: FirebaseAuth by lazy { Firebase.auth }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_start_page)
        init()
    }

    private fun init() {
        getCurrentUserDetails()
        getBasicApiData()
        doThis()
    }
    private fun getCurrentUserDetails() {
        val email = auth.currentUser?.email
        commonViewModel.getCurrentUserProfileByEmailViewModel(email!!)?.observe(this) { response ->
            if (response?.isError() == true) {
                Utils.showToast(this,"error: ${response.error}")
            }
            if (response?.isFailure() == true) {
                Utils.showToast(this,"failure: ${response.error}")
            }
            if (response?.isSuccess() == true) {
                val currentUser = (response.body as EmployeesResponse)
                DataStoreHelper.getInstance().saveCurrentUserProfile(currentUser)
                DataStoreHelper.getInstance().saveCurrentUserType(currentUser.userType!!)
                commonViewModel.getAllEmployees()
                commonViewModel.getAllProjects()
                commonViewModel.getAllTasks()
            }
        }
    }

    private fun getBasicApiData() {

    }

    private fun doThis() {

        val timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                DashBoardActivity.launchActivity(this@StartPageActivity)
                finish()
            }
        }, 3000)
    }
    companion object {
        fun launchActivity(activity: Activity) {
            val intent = Intent(activity, StartPageActivity::class.java)
            activity.startActivity(intent)
        }
    }
}