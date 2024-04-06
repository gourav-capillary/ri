package com.orion.ri.activities

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.databinding.ActivityProjectDetailsBinding
import com.orion.ri.model.project.OnGoingProjectData

class ProjectDetailsActivity : BaseActivity() {

    lateinit var binding: ActivityProjectDetailsBinding
    lateinit var projectItem:OnGoingProjectData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProjectDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialise()
    }

    private fun initialise() {
        getIntentData()
        basicSetup()
    }

    private fun getIntentData() {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            projectItem = intent.getSerializableExtra("ONGOING_PROJECT_DETAILS",OnGoingProjectData::class.java)!!
        } else {
            projectItem = intent.getSerializableExtra("ONGOING_PROJECT_DETAILS") as OnGoingProjectData
        }
    }

    private fun basicSetup() {

        println(projectItem)
        binding.projectName.text = projectItem.projectName
    }

    companion object{
        fun launchActivity(activity: Activity, ongoingProject: OnGoingProjectData){
            val intent = Intent(activity,ProjectDetailsActivity::class.java)
            intent.putExtra("ONGOING_PROJECT_DETAILS",ongoingProject)
            activity.startActivity(intent)
        }
    }
}