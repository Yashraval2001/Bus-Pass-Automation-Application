package com.example.buspass.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.example.buspass.R
import com.example.buspass.user_info
import com.example.buspass.userdetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_user_details.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [user_details.newInstance] factory method to
 * create an instance of this fragment.
 */
class user_details : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View= inflater.inflate(R.layout.fragment_user_details, container, false)
        val user:Button=view.findViewById(R.id.details_button)
        user.setOnClickListener {
            saveuserdata()
        }
        return view
    }
    private fun saveuserdata(){
        val name=editTextTextPersonName4.text.toString().trim()
        val dob=editTextDate.text.toString().trim()
        val mobile=editTextPhone.text.toString().trim()
        val emailid=editTextTextEmailAddress.text.toString().trim()
        val address=editTextTextPostalAddress.text.toString()
        val source=editText_source.text.toString()
        val destination=editText_destination.text.toString()
        val male=radiobutton_male.text.toString()
        val female=radiobutton_female.text.toString()
        val gender:String


        if(!Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text.toString()).matches()){
            editTextTextEmailAddress.error="email id is not valid"
            editTextTextEmailAddress.requestFocus()
            return
        }
        if(editTextTextPersonName4.text.toString().isEmpty()){
            editTextTextPersonName4.error="enter name"
            editTextTextPersonName4.requestFocus()
            return
        }
        if(editTextDate.text.toString().isEmpty()){
            editTextDate.error="enter dob"
            editTextDate.requestFocus()
            return
        }
        if(editTextPhone.text.toString().isEmpty()){
            editTextPhone.error="enter phone no"
            editTextPhone.requestFocus()
            return
        }
        if(editTextPhone.text.toString().length!=10){
            editTextPhone.error="enter valid no"
            editTextPhone.requestFocus()
            return
        }
        if(editTextTextEmailAddress.text.toString().isEmpty()){
            editTextTextEmailAddress.error="enter emailid"
            editTextTextEmailAddress.requestFocus()
            return
        }
        if(editTextTextPostalAddress.text.toString().isEmpty()){
            editTextTextPostalAddress.error="enter address"
            editTextTextPostalAddress.requestFocus()
            return
        }
        if(editText_source.text.toString().isEmpty()){
            editText_source.error="enter source address"
            editText_source.requestFocus()
            return
        }
        if(editText_destination.text.toString().isEmpty()){
            editText_destination.error="enter destination address"
            editText_destination.requestFocus()
            return
        }

        val ref= FirebaseDatabase.getInstance().getReference("Users")
        var auth= FirebaseAuth.getInstance()
        val userId=auth.currentUser!!.uid
        if (radiobutton_male.isChecked)
        {
            val user= userdetails(userId,name,dob,mobile,emailid,address,"male",source,destination)
            ref.child(userId).setValue(user)
                .addOnCompleteListener {
                    Toast.makeText(context, "successfully add", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed..", Toast.LENGTH_SHORT).show()
                }
        }
        if (radiobutton_female.isChecked)
        {
            val user= userdetails(userId,name,dob,mobile,emailid,address,"female",source,destination)
            ref.child(userId).setValue(user)
                .addOnCompleteListener {
                    Toast.makeText(context, "successfully add", Toast.LENGTH_SHORT).show()

                }
                .addOnFailureListener {
                    Toast.makeText(context, "Failed..", Toast.LENGTH_SHORT).show()
                }

        }






    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment user_details.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            user_details().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}