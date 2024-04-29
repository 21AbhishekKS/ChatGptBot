package com.abhi.chatgptbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abhi.chatgptbot.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUp_Activity : AppCompatActivity() {
    lateinit var signUpBinding: ActivitySignUpBinding
    val auth: FirebaseAuth =FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        val view = signUpBinding.root
        setContentView(view)


        signUpBinding.btnSignup.setOnClickListener{

            val userMail = signUpBinding.edtSignup.text.toString()
            val userPassword = signUpBinding.edtSignUpPassword.text.toString()

            signup(userMail,userPassword)

        }

        signUpBinding.tvLogin.setOnClickListener{

            val intent = Intent(this@SignUp_Activity,LoginActivity::class.java)
            startActivity(intent)

        }


    }

    private fun signup(usermail : String, userPassword : String) {


        auth.createUserWithEmailAndPassword(usermail,userPassword).addOnCompleteListener{task ->

            if (task.isSuccessful){
                Toast.makeText(this,"Accout is created",Toast.LENGTH_LONG).show()
                finish()
            }else{
                Toast.makeText(this,task.exception?.toString(),Toast.LENGTH_LONG).show()

            }


        }

    }
}