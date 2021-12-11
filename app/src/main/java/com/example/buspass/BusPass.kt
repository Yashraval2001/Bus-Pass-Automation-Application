package com.example.buspass

import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.lang.Exception

class BusPass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bus_pass)

        var buspassname:TextView=findViewById(R.id.buspassname)
        var buspasssource:TextView=findViewById(R.id.buspasssource)
        var busdestination:TextView=findViewById(R.id.buspassdestination)
        var busvalidfrom:TextView=findViewById(R.id.buspassvalidfrom)
        var busvalidupto:TextView=findViewById(R.id.busspassvalidupto)

        var buspassbtn:Button=findViewById(R.id.buspasssend)

        var buscard:CardView=findViewById(R.id.cardView3)





        var intnt = intent
        var bname = intnt.getStringExtra("name")
        var bsource=intnt.getStringExtra("source")
        var bdesination=intnt.getStringExtra("destination")
        var bvalidfrom=intnt.getStringExtra("validfrom")
        var bvalidupto=intnt.getStringExtra("validupto")
        var buspassid=intent.getStringExtra("id")
        var buspassemail=intnt.getStringExtra("email")

        buspassname.text=bname
        buspasssource.text=bsource
        busdestination.text=bdesination
        busvalidfrom.text=bvalidfrom
        busvalidupto.text=bvalidupto



        var retrivephoto= FirebaseStorage.getInstance().getReference().child(buspassid.toString()+"/userdocument/photo")
        try {
            var file= File.createTempFile("photo","jpg")
            retrivephoto.getFile(file)
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "photo fatch successfully..", Toast.LENGTH_SHORT).show()
                    var bitmap: Bitmap = BitmapFactory.decodeFile(file.absolutePath)
                    var bphoto:ImageView=findViewById(R.id.buspassphoto)
                    bphoto.setImageBitmap(bitmap)

                }
        }catch (e: Exception){
            e.printStackTrace()
        }

        buspassbtn.setOnClickListener {
            var bitmap:Bitmap= Bitmap.createBitmap(buscard.width,buscard.height,Bitmap.Config.ARGB_8888)
            var canvas:Canvas=Canvas(bitmap)
            buscard.draw(canvas)


            var bitmappath=MediaStore.Images.Media.insertImage(contentResolver,bitmap,"buspass",null)
            var uri:Uri= Uri.parse(bitmappath)


            var intent:Intent= Intent(Intent.ACTION_SEND)

            intent.data= Uri.parse("mailto:")
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(buspassemail.toString()))
            intent.putExtra(Intent.EXTRA_STREAM,uri)
            intent.putExtra(Intent.EXTRA_SUBJECT,"Buspass")
            intent.putExtra(Intent.EXTRA_TEXT,"your buspass genrate successfully")
            intent.setType("image/png;message/rfc822")
            startActivity(Intent.createChooser(intent,"share"))


            

        }
    }
}