package com.example.servercashout


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import java.math.RoundingMode
import  android.widget.ArrayAdapter
import java.text.DecimalFormat
import android.content.Intent
import android.widget.ListView

class display : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var editText: EditText
    lateinit var add:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.display)




    }
}