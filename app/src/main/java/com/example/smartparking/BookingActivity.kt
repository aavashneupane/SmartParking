package com.example.smartparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class BookingActivity : AppCompatActivity() {
    private lateinit var etName : EditText
    private lateinit var button: Button
    private lateinit var btnBack: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        etName = findViewById(R.id.etName)
        button = findViewById(R.id.button)
        btnBack = findViewById(R.id.btnBack)

        btnBack.setOnClickListener{
            startActivity(Intent(this@BookingActivity, MainActivity::class.java))
        }

        button.setOnClickListener{
            startActivity(Intent(this@BookingActivity, MainActivity::class.java))
        }
    }
}