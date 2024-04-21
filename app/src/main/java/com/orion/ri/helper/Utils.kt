package com.orion.ri.helper

import android.content.Context
import android.os.CountDownTimer
import android.widget.Toast
import com.orion.ri.R

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
     }
 }