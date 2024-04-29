package com.abhi.chatgptbot.models.imageResponse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.abhi.chatgptbot.R
import com.abhi.chatgptbot.databinding.ActivityForgePasswordBinding
import com.abhi.chatgptbot.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class ForgePassword_Activity : AppCompatActivity() {
    lateinit var forgetBinding: ActivityForgePasswordBinding
    val auth: FirebaseAuth = FirebaseAuth.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        forgetBinding = ActivityForgePasswordBinding.inflate(layoutInflater)
        val view = forgetBinding.root
        setContentView(view)

        forgetBinding.btnReset.setOnClickListener{

            val email = forgetBinding.edtSignup.text.toString()

            auth.sendPasswordResetEmail(email).addOnCompleteListener{task ->

                if (task.isSuccessful){
                    Toast.makeText(this, "Reset link sent to your email..!", Toast.LENGTH_LONG).show()
                    finish()

                }
                else
                {
                    Toast.makeText(this, task.exception?.toString(), Toast.LENGTH_LONG).show()

                }
            }

        }
    }
}