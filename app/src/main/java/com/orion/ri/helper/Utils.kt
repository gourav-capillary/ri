package com.orion.ri.helper

import com.orion.ri.R

 class Utils {


     companion object {
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
     }
 }