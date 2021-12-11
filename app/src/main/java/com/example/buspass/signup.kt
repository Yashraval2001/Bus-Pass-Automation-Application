package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*


class signup : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        auth= FirebaseAuth.getInstance()
        val signup:Button=findViewById(R.id.sign_up_btn)
        signup.setOnClickListener {
            signUpUser()

        }

    }
    private fun signUpUser(){
        if (username_signup.text.toString().isEmpty()){
            username_signup.error="enter username"
            username_signup.requestFocus()
            return
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email_signup.text.toString()).matches()){
            email_signup.error="email id is not valid"
            email_signup.requestFocus()
            return
        }

        if(password_signup.text.toString().isEmpty()){
            password_signup.error="enter password"
            password_signup.requestFocus()
            return
        }

        if(password_signup.text.toString()!=confirmpassword_signup.text.toString()){
            confirmpassword_signup.error="password doesn't match"
            confirmpassword_signup.requestFocus()
            return
        }
        auth.createUserWithEmailAndPassword(email_signup.text.toString(), password_signup.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user=auth.currentUser
                    user!!.sendEmailVerification()
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this,MainActivity::class.java))
                                finish()
                            }
                        }

                } else {
                    Toast.makeText(baseContext, "Sign up failed try again later",
                        Toast.LENGTH_SHORT).show()

                }

                // ...
            }
    }




}
