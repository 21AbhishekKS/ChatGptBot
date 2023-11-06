package com.abhi.chatgptbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abhi.chatgptbot.databinding.ActivityLoginBinding
import com.abhi.chatgptbot.models.imageResponse.ForgePassword_Activity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    lateinit var loginBinding: ActivityLoginBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        val view = loginBinding.root
        setContentView(view)

        loginBinding.btnSignup.setOnClickListener{

            val userMail = loginBinding.edtSignup.text.toString()
            val userPassword = loginBinding.edtSignUpPassword.text.toString()


            login(userMail,userPassword)

        }

        loginBinding.tvLogin.setOnClickListener{

            val intent = Intent(this@LoginActivity,SignUp_Activity::class.java)
            startActivity(intent)

        }

        loginBinding.tvForgetPassword.setOnClickListener{


            val intent = Intent(this@LoginActivity,ForgePassword_Activity::class.java)
            startActivity(intent)

        }



    }

    private fun login(usermail : String, userPassword : String) {

        auth.signInWithEmailAndPassword(usermail, userPassword).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_LONG).show()

            }

        }
    }

    override fun onStart() {
        super.onStart()

        val user = auth.currentUser

        if (user != null){

            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
            finish()

        }

    }
}