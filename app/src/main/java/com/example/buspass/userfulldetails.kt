package com.example.buspass

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_userfulldetails.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import java.io.File
import java.lang.Exception

class userfulldetails : AppCompatActivity() {
    lateinit var ref: DatabaseReference
    lateinit var validfrom:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_userfulldetails)


        var userid: TextView = findViewById(R.id.fetchid)
        var textviewfetchname:TextView=findViewById(R.id.fetchusername)
        var textviewdob:TextView=findViewById(R.id.fetchdob)
        var textviewmobile:TextView=findViewById(R.id.fetchmobile)
        var textviewemail:TextView=findViewById(R.id.fetchemail)
        var textviewaddress:TextView=findViewById(R.id.fetchaddress)
        var textviewgender:TextView=findViewById(R.id.fetchgender)
        var textviewsource:TextView=findViewById(R.id.fetchsource)
        var textviewdestination:TextView=findViewById(R.id.fetchdestination)

        var validfrom:EditText=findViewById(R.id.edittextviewvalidfrom)
        var validupto:EditText=findViewById(R.id.edittextviewvalidupto)

        var acceptbutton:Button=findViewById(R.id.adminagree)
        var deniedbutton:Button=findViewById(R.id.admindenied)

        var intnt = intent
        var useid = intnt.getStringExtra("id")
        userid.text=useid

        var retrivephoto=FirebaseStorage.getInstance().getReference().child(userid.text.toString()+"/userdocument/photo")
        try {
            var file= File.createTempFile("photo","jpg")
            retrivephoto.getFile(file)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "photo fatch successfully..", Toast.LENGTH_SHORT).show()
                    var bitmap:Bitmap=BitmapFactory.decodeFile(file.absolutePath)
                    var photo:ImageView=findViewById(R.id.retrivephoto)
                    photo.setImageBitmap(bitmap)

                }
        }catch (e:Exception){
            e.printStackTrace()
        }

        var retriveaadhar=FirebaseStorage.getInstance().getReference().child(userid.text.toString()+"/userdocument/aadhar")
        try {
            var fileaadhar= File.createTempFile("addhar","jpg")
            retriveaadhar.getFile(fileaadhar)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "addhar fatch successfully..", Toast.LENGTH_SHORT).show()
                    var bitmap:Bitmap=BitmapFactory.decodeFile(fileaadhar.absolutePath)
                    var aadhar:ImageView=findViewById(R.id.retriveaadhar)
                    aadhar.setImageBitmap(bitmap)

                }
        }catch (e:Exception){
            e.printStackTrace()
        }

        var retrivebonafide=FirebaseStorage.getInstance().getReference().child(userid.text.toString()+"/userdocument/bonafid")
        try {
            var filebonafide= File.createTempFile("bonafide","jpg")
            retrivebonafide.getFile(filebonafide)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "bonafide fatch successfully..", Toast.LENGTH_SHORT).show()
                    var bitmap:Bitmap=BitmapFactory.decodeFile(filebonafide.absolutePath)
                    var bonafide:ImageView=findViewById(R.id.retrivebonafide)
                    bonafide.setImageBitmap(bitmap)

                }
        }catch (e:Exception){
            e.printStackTrace()
        }





        ref= FirebaseDatabase.getInstance().getReference("Users/"+userid.text.toString())

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){

                    var fname=snapshot.child("name").getValue(String::class.java)
                    var fdob=snapshot.child("dob").getValue(String::class.java)
                    var fmobile=snapshot.child("mobile").getValue(String::class.java)
                    var femail=snapshot.child("emailid").getValue(String::class.java)
                    var faddress=snapshot.child("address").getValue(String::class.java)
                    var fgender=snapshot.child("gender").getValue(String::class.java)
                    var fsource=snapshot.child("source").getValue(String::class.java)
                    var fdestination=snapshot.child("destination").getValue(String::class.java)

                    textviewfetchname.text=fname.toString()
                    textviewdob.text=fdob.toString()
                    textviewmobile.text=fmobile.toString()
                    textviewemail.text=femail.toString()
                    textviewaddress.text=faddress.toString()
                    textviewgender.text=fgender.toString()
                    textviewsource.text=fsource.toString()
                    textviewdestination.text=fdestination.toString()
                    Toast.makeText(applicationContext, "UserDetails fetch Successfully..", Toast.LENGTH_SHORT).show()




                    }
                }


        })

        acceptbutton.setOnClickListener {
            if(validfrom.text.toString().isEmpty()){
                validfrom.error="enter date"
                validfrom.requestFocus()

            }
            if(validupto.text.toString().isEmpty()){
                validupto.error="enter date"
                validupto.requestFocus()


            }
            else {
                var intent: Intent = Intent(applicationContext, BusPass::class.java)
                intent.putExtra("name", textviewfetchname.text)
                intent.putExtra("source", textviewsource.text)
                intent.putExtra("destination", textviewdestination.text)
                intent.putExtra("validfrom", validfrom.text.toString())
                intent.putExtra("validupto", validupto.text.toString())
                intent.putExtra("id", userid.text.toString())
                intent.putExtra("email", textviewemail.text)
                startActivity(intent)
            }

        }
        deniedbutton.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto: "+textviewemail.text)
            intent.putExtra(Intent.EXTRA_EMAIL, "Buspass")
            intent.putExtra(Intent.EXTRA_SUBJECT, "BussPass")
            intent.putExtra(Intent.EXTRA_TEXT,"your buss pass invalid")

            startActivity(Intent.createChooser(intent,"send mail"))
        }


    }

}


