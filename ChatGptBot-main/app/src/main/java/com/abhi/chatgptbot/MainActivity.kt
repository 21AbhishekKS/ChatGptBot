package com.abhi.chatgptbot

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.abhi.chatgptbot.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        binding.chatWithBot.setOnClickListener{

            val intent = Intent(this,ChatActivity::class.java)
            startActivity(intent)

        }

        binding.generateImage.setOnClickListener{

            val intent = Intent(this,ImageGenerateActivity::class.java)
            startActivity(intent)

        }

        binding.btnLogout.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this@MainActivity,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}