package com.example.servercashout
import android.content.Context
import android.icu.text.Transliterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import  android.widget.ArrayAdapter

class adapter (val mctx: Context,val layoutResID:Int, val tipList :List<Tip>) :ArrayAdapter<Tip>(mctx,layoutResID,tipList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layoutInflater:LayoutInflater=LayoutInflater.from(mctx)
        val view:View= layoutInflater.inflate(layoutResID,null)
        val  takeHome=view.findViewById<TextView>(R.id.takeHomeAdapter)
        val tip=tipList[position]
        takeHome.text=tip.takeHome
        return view
    }
}