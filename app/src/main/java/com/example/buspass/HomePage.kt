package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        val user:Button=findViewById(R.id.user)
        val admin:Button=findViewById(R.id.admin)

        user.setOnClickListener {
            val intent:Intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        admin.setOnClickListener {
            val intent:Intent= Intent(this,admin_loginpage::class.java)
            startActivity(intent)
        }
    }
}