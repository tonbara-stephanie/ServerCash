package com.example.servercashout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class AnotherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_another)
        //get data from intent
        val intent = intent
        val takeHomeString = intent.getStringExtra("takeHome")
        val takeHomePercentageString = intent.getStringExtra("takeHomePercentage")
        val tipOutString = intent.getStringExtra("tipOut")
        val cash_computeString = intent.getStringExtra("cash_compute")

        //textview
        val takeHome = findViewById<TextView>(R.id.takeHome)
        val takePercentage = findViewById<TextView>(R.id.takePercentage)
        val cash_out = findViewById<TextView>(R.id.cash_out)
        val tip_out = findViewById<TextView>(R.id.tip_out)

        //setText
        takeHome.text = takeHomeString
        takePercentage.text=takeHomePercentageString
        cash_out.text=cash_computeString
        tip_out.text=tipOutString



    }
}