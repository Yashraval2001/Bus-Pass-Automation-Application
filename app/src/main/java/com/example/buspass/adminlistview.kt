package com.example.buspass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.userlist.*
import java.lang.StringBuilder

class adminlistview : AppCompatActivity() {

    lateinit var listview:ListView
    lateinit var userlist:MutableList<userdetails>
    lateinit var ref:DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adminlistview)
        listview=findViewById(R.id.userlistview)
        val list:ListView=findViewById(R.id.userlistview)



        userlist= mutableListOf()



        ref=FirebaseDatabase.getInstance().getReference("Users")
        /*listview.setOnItemClickListener = useradapter.OnItemClickListener { parent: AdapterView<*>!, view: View, position: Int, id: String ->
            // Get the selected item text from ListView
            val selectedItem = parent.getItemAtPosition(position) as String

            val intent = Intent(this, BookDetailActivity::class.java)
            startActivity(intent)
        }*/



        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot!!.exists()){
                    for (u in snapshot.children)
                    {

                        val user=u.getValue(userdetails::class.java)
                        userlist.add(user!!)

                    }
                    val userd=fetchdata(applicationContext,R.layout.userlist,userlist)
                    listview.adapter=userd

                }

            }

        })
        list.setOnItemClickListener{parent,view,position,id->
            if (position==0){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)
            }
            if (position==1){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==2){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==3){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==4){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==5){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==6){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==7){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==8){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==9){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
            if (position==10){
                var intent:Intent= Intent(this,userfulldetails::class.java)
                intent.putExtra("id",userlist.get(position).id)
                startActivity(intent)

            }
        }




    }
}