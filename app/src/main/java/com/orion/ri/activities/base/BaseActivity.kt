package com.orion.ri.activities.base

import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {
    fun enableBackButton(backButton: ImageView) {
        backButton.visibility = View.VISIBLE
        backButton.setOnClickListener {
            finish()
        }
    }
}