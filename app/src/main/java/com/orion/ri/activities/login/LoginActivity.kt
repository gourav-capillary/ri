package com.orion.ri.activities.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.orion.ri.activities.splash.StartPageActivity
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.activities.register.RegisterActivity
import com.orion.ri.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init() {
        basicSetup()
        setupListeners()
    }

    private fun setupListeners() {

        binding.register.setOnClickListener {
            RegisterActivity.newIntent(this)
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()

            if (email.isEmpty()) {
                Toast.makeText(this, "Email cant be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                Toast.makeText(this, "Password cant be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w("TAG", "signInWithEmail:failure", task.exception)
                        Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT)
                            .show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null) {
            //user doesnt exist
        } else {
            val intent = Intent(this, StartPageActivity::class.java)
            startActivity(intent)
//            finish()
        }
    }

    private fun basicSetup() {
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser != null) {
            StartPageActivity.launchActivity(this)
            finish()
        }
    }

    companion object {
        fun launchActivity(activity: Activity) {
            val loginActivity = Intent(activity, LoginActivity::class.java)
            activity.startActivity(loginActivity)
        }
    }
}