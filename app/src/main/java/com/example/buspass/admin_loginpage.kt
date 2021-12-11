package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_admin_loginpage.*
import kotlinx.android.synthetic.main.activity_home_page.*
import kotlin.math.sign

class admin_loginpage : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var adminlist: MutableList<admindata>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_loginpage)
        var signuptxt: TextView = findViewById(R.id.admin_signuptxt)
        var lgn: Button = findViewById(R.id.admin_login)
        signuptxt.setOnClickListener {
            val intent: Intent = Intent(this, admin_signup::class.java)
            startActivity(intent)

       }
        lgn.setOnClickListener {
            if(admin_email.text.toString().isEmpty()){
                admin_email.error="enter your emailid"
                admin_email.requestFocus()

            }
            if(admin_password.text.toString().isEmpty()){
                admin_password.error="enter your password"
                admin_password.requestFocus()


            }
            ref= FirebaseDatabase.getInstance().getReference("Admin")
            ref.addValueEventListener(object :ValueEventListener{
                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot!!.exists()) {

                        var adminemail = snapshot.child("email").getValue(String::class.java)
                        var adminpassword = snapshot.child("password").getValue(String::class.java)
                        var adminname = snapshot.child("adminname").getValue(String::class.java)

                        if (admin_email.text.toString().trim()==adminemail.toString() && admin_password.text.toString().trim()==adminpassword.toString() && adminname.toString()=="buspassadmin")
                        {
                            val intent:Intent= Intent(applicationContext,adminlistview::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(applicationContext, "invalid emailid or password", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

            })


        }
    }
}