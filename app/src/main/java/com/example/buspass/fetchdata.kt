package com.example.buspass

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class fetchdata (val mctx:Context,val layoutResId:Int,val userlist:List<userdetails>)
    :ArrayAdapter<userdetails>(mctx,layoutResId,userlist){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutinflater:LayoutInflater= LayoutInflater.from(mctx)
        val view:View=layoutinflater.inflate(layoutResId,null)
        val textviewid=view.findViewById<TextView>(R.id.textViewid)
        val textviewn=view.findViewById<TextView>(R.id.textviewname)
        val user=userlist[position]
        textviewid.text=user.name
        textviewn.text=user.id
        return view
    }
}