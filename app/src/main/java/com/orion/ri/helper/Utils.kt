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

         fun parseDate(dateString: String, inputFormat: String, outputFormat: String): String {
             val dateFormat = SimpleDateFormat(inputFormat, Locale.getDefault())
             val parsedDate = dateFormat.parse(dateString)
             val outputDateFormat = SimpleDateFormat(outputFormat, Locale.getDefault())
             return outputDateFormat.format(parsedDate)
         }

         fun sortTasksByDeadline(tasks: List<TaskResponse>): List<TaskResponse> {
             val inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
             val outputFormat = "dd/MM/yyyy"
             return tasks.sortedBy { task ->
                 parseDate(task.deadline, inputFormat, outputFormat)
             }
         }
         fun sortProjectsByDeadline(projects: List<ProjectResponse>): List<ProjectResponse> {
             val inputFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
             val outputFormat = "dd/MM/yyyy"
             return projects.sortedBy { project ->
                 parseDate(project.deadline, inputFormat, outputFormat)
             }
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