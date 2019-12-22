package com.example.servercashout


//Import all necessary API and Libraries
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
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.Month

class MainActivity : AppCompatActivity() {

    //  Declare Variables
    lateinit var grossReceipts: EditText
    lateinit var cashDue: EditText
    lateinit var cashOnHand: EditText
    lateinit var button: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Extract text from EditText
        grossReceipts=findViewById(R.id.gross_receipts)
        cashDue=findViewById(R.id.cash_due)
        cashOnHand=findViewById(R.id.cash_on_hand)
        button=findViewById(R.id.btn)


        //Called when the cash Out button is clicked
        button.setOnClickListener{
            // Curent date

            //Convert values from text to string
            var gross_receipts=grossReceipts.text.toString()
            var cash_due=cashDue.text.toString()
            var cash_on_hand=cashOnHand.text.toString()

            //Convert values from String  to Double
            var gross_compute=gross_receipts.toDouble()
            var cash_compute=cash_due.toDouble()
            var hand_compute=cash_on_hand.toDouble()
            var cash:Double=0.00
            var takeHomePercentage:Double
            var takeHome:Double
            val A:Double=(gross_compute/ 100)*3  //To East Side
            val B:Double= A+0.5  //To managers
            val tipOut=(gross_compute/100)
            cash_compute=cash_compute+B
            cash= cash_compute + tipOut
            cash_compute=cash
            val dec = DecimalFormat("#,###.00")


            val takeHomeString:String
            val takeHomePercentageString:String
            val tipOutString:String
            val cash_compute_string:String

            if(gross_receipts.isEmpty()){
                grossReceipts.error= "Please Enter Gross Receipts"

            }
            if(cash_due.isEmpty()){
                cashDue.error= "Please Enter Cash Due"
            }
            if(cash_on_hand.isEmpty()){
                cashOnHand.error= "Please Enter Cash in Hand"
            }

            //Only works when input fields are not empty
            if(!gross_receipts.isEmpty()&& !cash_due.isEmpty()&&!cash_on_hand.isEmpty()){

                if(cash_compute>=0) {


                    //Calculatin output values
                    takeHome = hand_compute - cash_compute
                    takeHomePercentage=(takeHome/gross_compute)*100
                    val takeHomeDec = dec.format(takeHome)
                    val takeHomePercentageDec = dec.format(takeHomePercentage)
                    val tipOutDec = dec.format(tipOut)
                    val cash_compute_dec=dec.format(cash_compute)

                    takeHomeString=takeHome.toString()
                    takeHomePercentageString=takeHomePercentage.toString()
                    tipOutString=tipOut.toString()
                    cash_compute_string=cash_compute.toString()

                    //creating intent to launch another Activity
                    val intent = Intent(this@MainActivity, AnotherActivity::class.java)


                    //Preparing values to export to new activity
                    intent.putExtra("takeHome", takeHomeDec)
                    intent.putExtra("takeHomePercentage", takeHomePercentageDec)
                    intent.putExtra("tipOut", tipOutDec)
                    intent.putExtra("cash_compute", cash_compute_dec)
                    startActivity(intent)


                    //Calls saveTIP() to Save
                    saveTip(takeHomeString,takeHomePercentageString,tipOutString,cash_compute_string)
                }
                else if(cash_compute<0){

                    takeHome=hand_compute+(Math.abs(cash_compute))
                    takeHomePercentage=(takeHome/gross_compute)*100
                    cash_compute=0.00


                    val takeHomeDec = dec.format(takeHome)
                    val takeHomePercentageDec = dec.format(takeHomePercentage)
                    val tipOutDec = dec.format(tipOut)
                    val cash_compute_dec=dec.format(cash_compute)

                    takeHomeString=takeHome.toString()
                    takeHomePercentageString=takeHomePercentage.toString()
                    tipOutString=tipOut.toString()
                    cash_compute_string=cash_compute.toString()

                    //creating intent to launch another Activity
                    val intent = Intent(this@MainActivity, AnotherActivity::class.java)



                    //Preparing values to export to new activity
                    intent.putExtra("takeHome", takeHomeDec)
                    intent.putExtra("takeHomePercentage", takeHomePercentageDec)
                    intent.putExtra("tipOut", tipOutDec)
                    intent.putExtra("cash_compute", cash_compute_dec)
                    startActivity(intent)

                    //Calls saveTIP() to Save data on Firebase
                    saveTip(takeHomeString,takeHomePercentageString,tipOutString,cash_compute_string)
                }


            }

        }
    }

    private fun saveTip(takeHomeString:String,takeHomePercentageString:String,tipOutString:String,cash_compute_string:String){


        //Method that adds value to database
        val database = FirebaseDatabase.getInstance()
        val ref = database.getReference("tips")
        var tipId=ref.push().key
        val id=tipId.toString()
        var tip= Tip(id,takeHomeString,takeHomePercentageString,tipOutString,cash_compute_string)
        ref.child(id).setValue(tip)

    }
}



