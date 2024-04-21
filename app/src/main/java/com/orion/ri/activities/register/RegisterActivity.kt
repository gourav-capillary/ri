package com.orion.ri.activities.register

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.orion.ri.activities.splash.StartPageActivity
import com.orion.ri.activities.base.BaseActivity
import com.orion.ri.activities.login.LoginActivity
import com.orion.ri.databinding.ActivityRegisterBinding

class RegisterActivity: BaseActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var auth:FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()
    }

    private fun init(){
        basicSetup()
        setupListeners()
    }

    private fun setupListeners() {

        binding.loginRedirect.setOnClickListener {
            LoginActivity.launchActivity(this)
        }
        binding.btnRegister.setOnClickListener {
            val email = binding.edEmail.text.toString()
            val password = binding.edPassword.text.toString()
            val name = binding.edName.text.toString()

            if (name.isEmpty()){
                Toast.makeText(this,"name cant be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (email.isEmpty()){
                Toast.makeText(this,"Email cant be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.isEmpty()){
                Toast.makeText(this,"Password cant be empty", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(baseContext,task.exception.toString(),Toast.LENGTH_SHORT,).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user == null){
            //show error
        }else{
            StartPageActivity.launchActivity(this)
        }

    }

    private fun basicSetup() {
        auth = Firebase.auth
        val currentUser = auth.currentUser
        if (currentUser != null) {
            StartPageActivity.launchActivity(this)
            finish()
        }

    }

    companion object {
        fun newIntent(activity: Activity) {
            val registerActivity = Intent(activity,RegisterActivity::class.java)
            activity.startActivity(registerActivity)
        }
    }
}