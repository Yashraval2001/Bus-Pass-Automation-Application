package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_signup.*
import kotlinx.android.synthetic.main.activity_signup.*

class admin_signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_signup)

        var admin_signup: Button = findViewById(R.id.admin_sign_up_btn)
        var adminemail:EditText=findViewById(R.id.adminemail_signup)
        var adminpassword:EditText=findViewById(R.id.adminpassword_signup)
        admin_signup.setOnClickListener {
            adminsignup()

        }

    }

    private fun adminsignup(){
        if (adminname_signup.text.toString().isEmpty()){
            adminname_signup.error="enter username"
            adminname_signup.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(adminemail_signup.text.toString()).matches()){
            adminemail_signup.error="email id is not valid"
            adminemail_signup.requestFocus()
            return
        }

        if(adminpassword_signup.text.toString().isEmpty()){
            adminpassword_signup.error="enter password"
            adminpassword_signup.requestFocus()
            return
        }

        if(adminpassword_signup.text.toString()!=adminconfirmpassword_signup.text.toString()){
            adminconfirmpassword_signup.error="password doesn't match"
            adminconfirmpassword_signup.requestFocus()
            return
        }



        var ref=FirebaseDatabase.getInstance().getReference()
        val admin=admindata(adminemail_signup.text.toString().trim(),adminpassword_signup.text.toString().trim(),adminname_signup.text.toString())
        ref.child("Admin").setValue(admin)
            .addOnCompleteListener {
            Toast.makeText(this, "data add successfully..", Toast.LENGTH_SHORT).show()
        }
            .addOnFailureListener {
                Toast.makeText(this, "failed..", Toast.LENGTH_SHORT).show()
            }


    }

    }



