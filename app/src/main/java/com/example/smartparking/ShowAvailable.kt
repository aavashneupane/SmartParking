package com.example.smartparking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.solver.state.ConstraintReference
import androidx.constraintlayout.solver.state.State
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ShowAvailable : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    private lateinit var tvAvailable:TextView
    private lateinit var layoutAvailable:LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        val availableslot = database.getReference("ParkingIOT/totalAvailable")
        val slot1 = database.getReference("ParkingIOT/SLOT1/status")
        val slot2 = database.getReference("ParkingIOT/SLOT2/status")
        val slot3 = database.getReference("ParkingIOT/SLOT3/status")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_available)

        tvAvailable=findViewById(R.id.tvAvailable)

        availableslot.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val slot = dataSnapshot.getValue(Long::class.java)
                if (slot != null) {



                }
            }
            override fun onCancelled(error: DatabaseError)
            {
                Toast.makeText(this@ShowAvailable, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })


        layoutAvailable=findViewById(R.id.layoutAvailable)

        layoutAvailable.setOnClickListener {
            startActivity(
                Intent(
                    this@ShowAvailable,
                    MainActivity::class.java
                )
            )

        }



    }
}