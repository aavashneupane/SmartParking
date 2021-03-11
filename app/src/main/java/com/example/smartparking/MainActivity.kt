package com.example.smartparking

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var wholelayout:LinearLayout
    private lateinit var linearSlot1:LinearLayout
    private lateinit var linearSlot2:LinearLayout
    private lateinit var linearSlot3:LinearLayout

    private lateinit var tvSlot1:TextView
    private lateinit var tvSlot2:TextView
    private lateinit var tvSlot3:TextView
    private lateinit var tvDemo:TextView

    private lateinit var tvBook1:TextView
    private lateinit var tvBook2:TextView
    private lateinit var tvBook3:TextView

    private lateinit var btngetout:Button

    private lateinit var btnbook1:Button
    private lateinit var btnbook2:Button
    private lateinit var btnbook3:Button

    private lateinit var btnopendoor1:Button
    private lateinit var btnopendoor2:Button
    private lateinit var btnopendoor3:Button

    private lateinit var etCode1:EditText
    private lateinit var etCode2:EditText
    private lateinit var etCode3:EditText

    override fun onCreate(savedInstanceState: Bundle?) {


        val database = FirebaseDatabase.getInstance()
        val slot1 = database.getReference("ParkingIOT/SLOT1/status")
        val slot2 = database.getReference("ParkingIOT/SLOT2/status")
        val slot3 = database.getReference("ParkingIOT/SLOT3/status")

        val bookedby1 = database.getReference("ParkingIOT/SLOT1/bookedby/name")
        val getin1 = database.getReference("ParkingIOT/SLOT1/bookedby/getin")
        val getout1 = database.getReference("ParkingIOT/SLOT1/bookedby/getout")
        val vacant1 = database.getReference("ParkingIOT/SLOT1/vacant")
        val timein1 = database.getReference("ParkingIOT/SLOT1/bookedby/timein")


        val bookedby2 = database.getReference("ParkingIOT/SLOT2/bookedby/name")
        val getin2 = database.getReference("ParkingIOT/SLOT2/bookedby/getin")
        val getout2 = database.getReference("ParkingIOT/SLOT2/bookedby/getout")
        val vacant2 = database.getReference("ParkingIOT/SLOT2/vacant")
        val timein2 = database.getReference("ParkingIOT/SLOT2/bookedby/timein")


        val bookedby3 = database.getReference("ParkingIOT/SLOT3/bookedby/name")
        val getin3 = database.getReference("ParkingIOT/SLOT3/bookedby/getin")
        val getout3 = database.getReference("ParkingIOT/SLOT3/bookedby/getout")
        val vacant3 = database.getReference("ParkingIOT/SLOT3/vacant")
        val timein3 = database.getReference("ParkingIOT/SLOT3/bookedby/timein")




        val opendoor = database.getReference("ParkingIOT/opendoor")



        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wholelayout=findViewById(R.id.wholelayout)
        linearSlot1=findViewById(R.id.linearSlot1)
        linearSlot2=findViewById(R.id.linearslot2)
        linearSlot3=findViewById(R.id.linearSlot3)

        tvSlot1=findViewById(R.id.tvslot1)
        tvSlot2=findViewById(R.id.tvslot2)
        tvSlot3=findViewById(R.id.tvslot3)
        tvDemo=findViewById(R.id.tvdemo)

        tvBook1=findViewById(R.id.tvbook1)
        tvBook2=findViewById(R.id.tvbook2)
        tvBook3=findViewById(R.id.tvbook3)

        etCode1=findViewById(R.id.etCode1)
        etCode2=findViewById(R.id.etCode2)
        etCode3=findViewById(R.id.etCode3)

        btnbook1=findViewById(R.id.btnbook1)
        btnbook2=findViewById(R.id.btnbook2)
        btnbook3=findViewById(R.id.btnbook3)

        btngetout=findViewById(R.id.btngetout)

        btnopendoor1=findViewById(R.id.btnopendoor1)
        btnopendoor2=findViewById(R.id.btnopendoor2)
        btnopendoor3=findViewById(R.id.btnopendoor3)

        //initially open door is disabled
        btnopendoor1.setVisibility(View.GONE)
        btnopendoor2.setVisibility(View.GONE)
        btnopendoor3.setVisibility(View.GONE)

        val enteredCode1=etCode1.getText().toString()
        val enteredCode2=etCode2.getText().toString()
        val enteredCode3=etCode3.getText().toString()


        /////////////////event listener to check pin
        //for slot1
        btngetout.setOnClickListener {
            startActivity(
                Intent(
                    this@MainActivity,
                    GetOutActivity::class.java
                )
            )
        }

        btnbook1.setOnClickListener {

            getin1.addValueEventListener(object : ValueEventListener {
                @RequiresApi(Build.VERSION_CODES.O)
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode1")
                    if (getin != null) {

                        if(getin==etCode1.getText().toString()){
                            Toast.makeText(this@MainActivity, "Correct Code", Toast.LENGTH_SHORT).show()
                      //      val in1=getTime()
                            timein1.setValue(ServerValue.TIMESTAMP)

                            //timein1.setValue()
                            startAlert();
                            btnopendoor1.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(this@MainActivity, "cannot get value", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })


        }
        btnbook2.setOnClickListener {
            getin2.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode2")
                    if (getin != null) {

                        if(getin==etCode2.getText().toString()){
                            Toast.makeText(this@MainActivity, "Correct Code", Toast.LENGTH_SHORT).show()
                            timein2.setValue(ServerValue.TIMESTAMP)
                            startAlert();
                            btnopendoor2.setVisibility(View.VISIBLE);

                        }else{
                            Toast.makeText(this@MainActivity, "cannot get value", Toast.LENGTH_SHORT).show()

                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })

        }
        btnbook3.setOnClickListener {
            getin3.addValueEventListener(object : ValueEventListener {

                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    val getin = dataSnapshot.getValue(Long::class.java).toString()
                    Log.d("getin data is", "Value is: $getin")
                    Log.d("entered data is", "Value is: $enteredCode3")
                    if (getin != null) {

                        if(getin==etCode3.getText().toString()){
                            Toast.makeText(this@MainActivity, "Correct Code", Toast.LENGTH_SHORT).show()

                            timein3.setValue(ServerValue.TIMESTAMP)
                            startAlert()
                            btnopendoor3.setVisibility(View.VISIBLE);
                        }else{
                            Toast.makeText(this@MainActivity, "cannot get value", Toast.LENGTH_SHORT).show()


                        }

                    }
                }
                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w("Parking value", "Failed to read value.", error.toException())
                    Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
                }
            })

        }

        // onclick listener to open door
        btnopendoor1.setOnClickListener {
            opendoor.setValue(true)
//            Thread.sleep(10000)
//            opendoor.setValue(false)
            btnopendoor1.setVisibility(View.GONE);
            startActivity(
                Intent(
                    this@MainActivity,
                    ShowAvailable::class.java
                )
            )
        }
        btnopendoor2.setOnClickListener {
            opendoor.setValue(true)
            Thread.sleep(10000)
            opendoor.setValue(false)
            btnopendoor2.setVisibility(View.GONE)
            startActivity(
                Intent(
                    this@MainActivity,
                    ShowAvailable::class.java
                )
            )

        }
        btnopendoor3.setOnClickListener {
            opendoor.setValue(true)
            Thread.sleep(10000)
            opendoor.setValue(false)
            btnopendoor3.setVisibility(View.GONE);
            startActivity(
                Intent(
                    this@MainActivity,
                    ShowAvailable::class.java
                )
            )
        }



/////////////// value event listener for sensor

        slot1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                Log.d("Sensor data", "Value is: $value")

                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 1 is occupied", Toast.LENGTH_SHORT).show()
                        tvSlot1.setText("Occupied")
                //        book1.setValue(false);
                        btnbook1.setVisibility(View.GONE);
                        etCode1.setVisibility(View.GONE);
                        btnopendoor1.setVisibility(View.GONE);


                    }else{
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 1 is empty", Toast.LENGTH_SHORT).show()
                        tvSlot1.setText("Empty")
                  //      book1.setValue(false);
                        btnbook1.setVisibility(View.VISIBLE);
                        etCode1.setVisibility(View.VISIBLE);
                 //       btnopendoor1.setVisibility(View.VISIBLE);
                    }

                }
            }
         override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })

        ///for slot 2

        slot2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                Log.d("Sensor data", "Value is: $value")

                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 2 is occupied", Toast.LENGTH_SHORT).show()
                        tvSlot2.setText("Occupied")
                        //        book1.setValue(false);
                        btnbook2.setVisibility(View.GONE);
                        etCode2.setVisibility(View.GONE);
                        btnopendoor2.setVisibility(View.GONE);


                    }else{
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 2 is empty", Toast.LENGTH_SHORT).show()
                        tvSlot2.setText("Empty")
                        //      book1.setValue(false);
                        btnbook2.setVisibility(View.VISIBLE);
                        etCode2.setVisibility(View.VISIBLE);
                  //      btnopendoor2.setVisibility(View.VISIBLE);
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })

        //for slot 3

        slot3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                Log.d("Sensor data", "Value is: $value")

                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 3 is occupied", Toast.LENGTH_SHORT).show()
                        tvSlot3.setText("Occupied")
                        //        book1.setValue(false);
                        btnbook3.setVisibility(View.GONE);
                        etCode3.setVisibility(View.GONE);
                        btnopendoor3.setVisibility(View.GONE);


                    }else{
                        Toast.makeText(this@MainActivity, "Cuurent parking status in slot 2 is empty", Toast.LENGTH_SHORT).show()
                        tvSlot3.setText("Empty")
                        //      book1.setValue(false);
                        btnbook3.setVisibility(View.VISIBLE);
                        etCode3.setVisibility(View.VISIBLE);
                    //    btnopendoor3.setVisibility(View.VISIBLE);
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })

        //////////////////value event listener for booked or not
///for slot1
        vacant1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Vacancy of 1 is empty", Toast.LENGTH_SHORT).show()
                        tvBook1.setText("Empty")
                        //        book1.setValue(false);
                        btnbook1.setVisibility(View.VISIBLE);
                        etCode1.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(this@MainActivity, "Vacancy of slot 2 is closed", Toast.LENGTH_SHORT).show()
                        //tvSlot1.setText("Empty")
                        tvBook1.setText("Closed")
                        //      book1.setValue(false);
                 //       btnbook1.setVisibility(View.GONE);
                       // etCode1.setVisibility(View.GONE);
                        //       btnopendoor1.setVisibility(View.VISIBLE);
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })
///for slot 2
        vacant2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Vacancy of 2 is empty", Toast.LENGTH_SHORT).show()
                        tvBook2.setText("Empty")
                        //        book1.setValue(false);
                        btnbook2.setVisibility(View.VISIBLE);
                        etCode2.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(this@MainActivity, "Vacancy of slot 2 is closed", Toast.LENGTH_SHORT).show()
                        //tvSlot1.setText("Empty")
                        tvBook2.setText("Closed")
                        //      book1.setValue(false);
                 //       btnbook2.setVisibility(View.GONE);
                   //     etCode2.setVisibility(View.GONE);
                        //       btnopendoor1.setVisibility(View.VISIBLE);
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })
/// for slot3
        vacant3.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(Boolean::class.java)
                if (value != null) {

                    if(value==true){
                        Toast.makeText(this@MainActivity, "Vacancy of 3 is empty", Toast.LENGTH_SHORT).show()
                        tvBook3.setText("Empty")
                        //        book1.setValue(false);
                        btnbook3.setVisibility(View.VISIBLE);
                        etCode3.setVisibility(View.VISIBLE);

                    }else{
                        Toast.makeText(this@MainActivity, "Vacancy of slot 3 is closed", Toast.LENGTH_SHORT).show()
                        //tvSlot1.setText("Empty")
                        tvBook3.setText("Closed")
                     //   etCode3.setVisibility(View.GONE);

                        //      book1.setValue(false);
                       // btnbook3.setVisibility(View.GONE);
                        //       btnopendoor1.setVisibility(View.VISIBLE);
                    }

                }
            }
            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("Parking value", "Failed to read value.", error.toException())
                Toast.makeText(this@MainActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })



    }

    fun startAlert(){
        val builder = AlertDialog.Builder(this)

        builder.setTitle("Open Gate")

        builder.setMessage("Please press Open Door to unlock the gate")
        builder.setIcon(android.R.drawable.ic_dialog_alert)


        builder.setPositiveButton("OK"){dialogInterface, which ->

        }



        val alertDialog: AlertDialog = builder.create()

        alertDialog.setCancelable(false)
        alertDialog.show()
    }

//    fun checkPass(){
//

//        if (a==b){
//            return
//        }else{
//            Toast.makeText(this@MainActivity, "Incorrect PIN. Try again Or book now.", Toast.LENGTH_SHORT).show()
//        }
//    }


    //to claculate time



}