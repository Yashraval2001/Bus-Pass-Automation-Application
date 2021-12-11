package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*


class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth= FirebaseAuth.getInstance()

        val s_textbox:TextView=findViewById(R.id.textView6)
        val login:Button=findViewById(R.id.user_login)
        s_textbox.setOnClickListener {
            val intent:Intent= Intent(this,signup::class.java)
            startActivity(intent)
        }

        login.setOnClickListener {
            doLogin()
        }


    }
    private fun doLogin(){

        if(!Patterns.EMAIL_ADDRESS.matcher(email_login.text.toString()).matches()){
            email_signup.error="email id is not valid"
            email_signup.requestFocus()
            return
        }

        if(password_login.text.toString().isEmpty()){
            password_signup.error="enter password"
            password_signup.requestFocus()
            return
        }
        auth.signInWithEmailAndPassword(email_login.text.toString(), password_login.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                    // ...
                }

                // ...
            }
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    fun updateUI(currentUser : FirebaseUser?)
    {
        if (currentUser !=null){
            if (currentUser.isEmailVerified){
            startActivity(Intent(this,user_info::class.java))
            finish()
        }else{
                Toast.makeText(baseContext, "please verify your email.",
                    Toast.LENGTH_SHORT).show ()
            }

            }
        else{
            Toast.makeText(baseContext, "Login failed.",
                Toast.LENGTH_SHORT).show ()
        }

    }
    
}