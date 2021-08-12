package com.example.blooddonation

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weight = findViewById<EditText>(R.id.weight).text
        val age = findViewById<EditText>(R.id.age).text
        val q1 = findViewById<RadioGroup>(R.id.q1)
        val q2 = findViewById<RadioGroup>(R.id.q2)
        val link = findViewById<TextView>(R.id.link)


        val evalButton = findViewById<Button>(R.id.button)
        var sleep = ""
        var feelingwell = ""
        val url = "http://www.pdn.gov.my"

        link.setOnClickListener {

            Toast.makeText(this, "Directing url, please wait.", Toast.LENGTH_SHORT).show()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url.toString()))
            startActivity(intent);


        }



        q1?.setOnCheckedChangeListener { group, checkedId ->

            if (R.id.radioButton1 == checkedId) {
                sleep = "Yes"
            } else {
                sleep = "No"
            }

        }

        q2?.setOnCheckedChangeListener { group, checkedId ->

            if (R.id.radioButton3 == checkedId) {
                feelingwell = "No"
            } else {
                feelingwell = "Yes"
            }
        }


        evalButton.setOnClickListener {

            validation(it, weight, age, sleep, feelingwell)
        }


    }


    private fun validation(
        view: View,
        weight: Editable,
        age: Editable,
        sleep: String,
        feelingwell: String
    ) {

        try {
            if (Integer.parseInt(weight.toString()) > 44 && Integer.parseInt(age.toString()) > 17 && Integer.parseInt(
                    age.toString()
                ) < 61 && feelingwell.toString() == "Yes" && sleep.toString() == "Yes"
            ) {

                if (Integer.parseInt(weight.toString()) > 44 && Integer.parseInt(weight.toString()) < 51) {

                    findViewById<TextView>(R.id.result).text = "You are eligible to donate 350ml"
                } else {
                    findViewById<TextView>(R.id.result).text = "You are eligible to donate 450ml"
                }

            } else {

                findViewById<TextView>(R.id.result).text =
                    "Sorry, you did not meet some requirement to donate blood."
            }
        } catch (e: Exception) {

            findViewById<TextView>(R.id.result).text =
                "Invalid input, please check your details input"
        }


    }
}