package com.example.buspass.ui

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.buspass.R
import com.example.buspass.user_info
import com.example.buspass.userdetails
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_document.*
import kotlinx.android.synthetic.main.fragment_user_details.*
import kotlinx.android.synthetic.main.nav_header_main.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [document.newInstance] factory method to
 * create an instance of this fragment.
 */
class document : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var filepath:Uri


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

        val view:View= inflater!!.inflate(R.layout.fragment_document, container, false)
        val photo: Button =view.findViewById(R.id.document_photo)
        val add:Button=view.findViewById(R.id.document_add)
        val aadharbtn:Button=view.findViewById(R.id.aadharaddbtn)
        val bonafidebtn:Button=view.findViewById(R.id.bonafideaddbtn)
        val aadhar:Button=view.findViewById(R.id.document_aadhar)
        val bonafide:Button=view.findViewById(R.id.document_bonafide)
        var auth=FirebaseAuth.getInstance()
        val userId=auth.currentUser!!.uid



        photo.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent,"choose pic"),111)
         add.setOnClickListener {
             if (filepath!=null){


                 var imageref=FirebaseStorage.getInstance().reference.child(userId+"/"+"userdocument/photo")
                 imageref.putFile(filepath).addOnSuccessListener {
                     var ref=FirebaseDatabase.getInstance().getReference("Users/"+userId)
                     Toast.makeText(context,"photo uploaded successfully",Toast.LENGTH_LONG).show()
                     Toast.makeText(context, userId, Toast.LENGTH_SHORT).show()
                     var iurl=FirebaseStorage.getInstance().reference.child(userId)
                         .child("userdocument")
                         .child("photo")

                 }

                 var image=FirebaseStorage.getInstance().reference.child(userId+"/"+"userdocument/photo")
                              }
             else{
                 Toast.makeText(context,"try again",Toast.LENGTH_LONG).show()

             }
         }
        }
        aadhar.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent,"choose pic"),222)
            aadharbtn.setOnClickListener {
                if (filepath!=null){

                    var imageref=FirebaseStorage.getInstance().reference.child(userId+"/"+"userdocument/aadhar")
                    imageref.putFile(filepath).addOnSuccessListener {
                        Toast.makeText(context,"aadharcard uploaded successfully",Toast.LENGTH_LONG).show()
                    }

                }
                else{
                    Toast.makeText(context,"try again",Toast.LENGTH_LONG).show()

                }

            }
        }
        bonafide.setOnClickListener {
            val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.setType("image/*")
            startActivityForResult(Intent.createChooser(intent,"choose pic"),333)
            bonafidebtn.setOnClickListener {
                if (filepath!=null){

                    var imageref=FirebaseStorage.getInstance().reference.child(userId+"/"+"userdocument/bonafid")
                    imageref.putFile(filepath).addOnSuccessListener {
                        Toast.makeText(context,"receipt uploaded successfully",Toast.LENGTH_LONG).show()
                    }

                }
                else{
                    Toast.makeText(context,"try again",Toast.LENGTH_LONG).show()

                }
            }
        }





        return view


    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==111 && resultCode==Activity.RESULT_OK && data!=null)
        {
            filepath=data.data!!

            var bitmap:Bitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,filepath)
            iphoto_view.setImageBitmap(bitmap)

        }
        if (requestCode==222 && resultCode==Activity.RESULT_OK && data!=null)
        {
            filepath=data.data!!

            var bitmap:Bitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,filepath)
            iaadhar_view.setImageBitmap(bitmap)

        }
        if (requestCode==333 && resultCode==Activity.RESULT_OK && data!=null)
        {
            filepath=data.data!!

            var bitmap:Bitmap=MediaStore.Images.Media.getBitmap(requireActivity().contentResolver,filepath)
            ibonafide_view.setImageBitmap(bitmap)

        }

    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment document.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            document().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    
}



