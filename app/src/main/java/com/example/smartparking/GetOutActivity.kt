package com.example.smartparking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GetOutActivity : AppCompatActivity() {

    private lateinit var btngetout1:Button
    private lateinit var etgetout1:EditText

    private lateinit var btngetout2:Button
    private lateinit var etgetout2:EditText

    private lateinit var btngetout3:Button
    private lateinit var etgetout3:EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        val database = FirebaseDatabase.getInstance()


        val getin1 = database.getReference("ParkingIOT/SLOT1/bookedby/getin")
        val getout1 = database.getReference("ParkingIOT/SLOT1/bookedby/getout")

        val getin2 = database.getReference("ParkingIOT/SLOT2/bookedby/getin")
        val getin3 = database.getReference("ParkingIOT/SLOT3/bookedby/getin")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_out)

        btngetout1=findViewById(R.id.btngetout1)
        etgetout1=findViewById(R.id.etgetout1)

        btngetout2=findViewById(R.id.btngetout2)
        etgetout2=findViewById(R.id.etgetout2)

        btngetout3=findViewById(R.id.btngetout3)
        etgetout3=findViewById(R.id.etgetout3)

        val enteredCode1=etgetout1.getText().toString()
        val enteredCode2=etgetout2.getText().toString()
        val enteredCode3=etgetout3.getText().toString()

// for slot1
        btngetout1.setOnClickListener {
            generateTransaction()

            getin1.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode1")
                    if (getin != null) {

                        if(getin==etgetout1.getText().toString()){
                            Toast.makeText(this@GetOutActivity, "Correct Code for slot1. Opening gate", Toast.LENGTH_SHORT).show()
                          //  startAlert();
                          //  btnopendoor1.setVisibility(View.VISIBLE);

                        }else{
                            Toast.makeText(this@GetOutActivity, "Incorrect pin", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })


        }
//for slot 2
        btngetout2.setOnClickListener {

            getin2.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode1")
                    if (getin != null) {

                        if(getin==etgetout2.getText().toString()){
                            Toast.makeText(this@GetOutActivity, "Correct Code for slot2. Opening gate", Toast.LENGTH_SHORT).show()
                            //  startAlert();
                            //  btnopendoor1.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(this@GetOutActivity, "Incorrect pin", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })


        }

        //for slot3
        btngetout3.setOnClickListener {

            getin3.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode1")
                    if (getin != null) {

                        if(getin==etgetout3.getText().toString()){
                            Toast.makeText(this@GetOutActivity, "Correct Code for slot3. Opening gate", Toast.LENGTH_SHORT).show()
                            //  startAlert();
                            //  btnopendoor1.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(this@GetOutActivity, "Incorrect pin", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })


        }


    }
    fun generateTransaction(){
        val randomNumber2 = (9999..99999).random()
        Toast.makeText(this@GetOutActivity, "Transaction id is $randomNumber2", Toast.LENGTH_SHORT).show()
        return
    }

}