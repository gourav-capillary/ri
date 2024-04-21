package com.orion.ri.helper

import DataStoreHelper
import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import com.orion.ri.R
import com.orion.ri.model.response.ProjectResponse
import com.orion.ri.model.response.TaskResponse
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class Utils {


     companion object {

         fun scheduleTaskWithCountDownTimer(delayMillis: Long, task: () -> Unit) {
             object : CountDownTimer(delayMillis, delayMillis) {
                 override fun onTick(millisUntilFinished: Long) {
                     // Not used in this implementation
                 }

                 override fun onFinish() {
                     task.invoke()
                 }
             }.start()
         }
         public fun getBackgroundByPosition(position: Int): Int {
             when (position % 5) {
                 0 -> {
                     return R.drawable.task_background_1
                 }

                 1 -> {
                     return R.drawable.task_background_2
                 }

                 2 -> {
                     return R.drawable.task_background_3
                 }

                 3 -> {
                     return R.drawable.task_background_4
                 }

                 4 -> {
                     return R.drawable.task_background_5
                 }
             }
             return R.drawable.task_background_5
         }

         fun isAPISuccess(code: Int): Boolean {
             return code >= 200 && code <= 300
         }

         fun showToast(context: Context, msg: String) {
             Toast.makeText(
                 context,
                 msg,
                 Toast.LENGTH_SHORT
             ).show()
         }

         fun parseDateFromString(dateString:String, format:SimpleDateFormat): Date {
            return format.parse(dateString)

         }



         fun sortTasksByDeadline(tasks: List<TaskResponse>): List<TaskResponse> {
             val sortedTaskList = tasks.sortedBy {
                 val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                 val date = format.parse(it.deadline)
                 date
             }
             return sortedTaskList
         }
         fun sortProjectsByDeadline(projects: List<ProjectResponse>): List<ProjectResponse> {
             val sortedProjectsList = projects.sortedBy {
                 val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
                 val date = format.parse(it.deadline)
                 date
             }
             return sortedProjectsList
         }

         fun filterTaskForUser(tasks: List<TaskResponse>):List<TaskResponse> {
             val currentUser = DataStoreHelper.getInstance().getCurrentUserProfile()
             val resultTasks:MutableList<TaskResponse> = mutableListOf()
             for (task in tasks){
                 for (ids in task.employees){
                     if (ids.equals(currentUser.id)){
                         resultTasks.add(task)
                         break
                     }
                 }
             }
             return resultTasks

         }
         fun filterProjectsForUser(projects: List<ProjectResponse>):List<ProjectResponse> {
             val currentUser = DataStoreHelper.getInstance().getCurrentUserProfile()
             val resultProjects:MutableList<ProjectResponse> = mutableListOf()
             for (project in projects){
                 for (ids in project.employees){
                     if (ids.equals(currentUser.id)){
                         resultProjects.add(project)
                         break
                     }
                 }
             }
             return resultProjects

         }
     }




     object DateFormats {
         val dateFormatDDMMYYYY = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
         val dateFormatUnknown = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",Locale.getDefault())

     }
 }