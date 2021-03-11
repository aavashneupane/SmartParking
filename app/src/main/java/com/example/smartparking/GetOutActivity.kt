package com.example.smartparking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class GetOutActivity : AppCompatActivity() {

    private lateinit var btngetout1:Button
    private lateinit var etgetout1:EditText

    private lateinit var btngetout2:Button
    private lateinit var etgetout2:EditText

    private lateinit var btngetout3:Button
    private lateinit var etgetout3:EditText

    private lateinit var bookBy : String
    private lateinit var timeIn : String
    private lateinit var timeOut : String

    val database = FirebaseDatabase.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        val bookedby1 = database.getReference("ParkingIOT/SLOT1/bookedby/name")
        val bookedby2 = database.getReference("ParkingIOT/SLOT2/bookedby/name")
        val bookedby3 = database.getReference("ParkingIOT/SLOT3/bookedby/name")

        val getin1 = database.getReference("ParkingIOT/SLOT1/bookedby/getin")
        val getout1 = database.getReference("ParkingIOT/SLOT1/bookedby/getout")
        val getin2 = database.getReference("ParkingIOT/SLOT2/bookedby/getin")
        val getin3 = database.getReference("ParkingIOT/SLOT3/bookedby/getin")

        val timein1 = database.getReference("ParkingIOT/SLOT1/bookedby/timein")
        val timein2 = database.getReference("ParkingIOT/SLOT2/bookedby/timein")
        val timein3 = database.getReference("ParkingIOT/SLOT3/bookedby/timein")

        val timeout1 = database.getReference("ParkingIOT/SLOT1/bookedby/timeout")
        val timeout2 = database.getReference("ParkingIOT/SLOT2/bookedby/timeout")
        val timeout3 = database.getReference("ParkingIOT/SLOT3/bookedby/timeout")


        val transactions = database.getReference("transactions")
        val openoutdoor = database.getReference("ParkingIOT/openoutdoor")

      //  val getuser=database.getReference("userDetails")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_out)

        btngetout1=findViewById(R.id.btngetout1)
        etgetout1=findViewById(R.id.etgetout1)

        btngetout2=findViewById(R.id.btngetout2)
        etgetout2=findViewById(R.id.etgetout2)

        btngetout3=findViewById(R.id.btngetout3)
        etgetout3=findViewById(R.id.etgetout3)

        val enteredCode1=etgetout1.text.toString()
        val enteredCode2=etgetout2.getText().toString()
        val enteredCode3=etgetout3.getText().toString()

// for slot1
        btngetout1.setOnClickListener {

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

                            timeout1.setValue(ServerValue.TIMESTAMP)


                            var email: String=bookedby1.get().toString()
                            /////// to get userid from booking slot1
//                            Toast.makeText(this@GetOutActivity, "email $email", Toast.LENGTH_SHORT).show()

                            Toast.makeText(this@GetOutActivity, "email $email.text.toString()", Toast.LENGTH_SHORT).show()
//                            val getintime: Task<DataSnapshot> =getin1.get()
//                            val getouttime: Task<DataSnapshot> =getout1.get()
//


                            bookBy=bookedby1.get().toString()
                            timeIn=timein1.get().toString()
//                            timeOut=timeout1().get().toString()
                            val d=timeout1()

                            var bookingsmodel=transactionData(email ,timeIn,d)
                            transactions.push().setValue(bookingsmodel)

                            calculateTime1()

                          //  Toast.makeText(this@GetOutActivity, "Total time is $d", Toast.LENGTH_SHORT).show()
                            openoutdoor.setValue(true)
                            val handler = Handler()
                            handler.postDelayed({
                                openoutdoor.setValue(false)
                            }, 32000)


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
                            openoutdoor.setValue(true)
                            //  startAlert();
                            //  btnopendoor1.setVisibility(View.VISIBLE);

                            timeout2.setValue(ServerValue.TIMESTAMP)

                            val d=bookedby2.get().toString()
                            val e=timein2.get().toString()
                            val f=timeout2.get().toString()

//                            var bookingsmodel=transactionData(d,e,f)
//                            transactions.push().setValue(bookingsmodel)


                            openoutdoor.setValue(true)
                            val handler = Handler()
                            handler.postDelayed({
                                openoutdoor.setValue(false)
                            }, 32000)
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

                            timeout3.setValue(ServerValue.TIMESTAMP)

                            val g=bookedby3.get().toString()
                            val h=timein3.get().toString()
                            val i=timeout3.get().toString()

//                            var bookingsmodel=transactionData(g,h,i)
//                            transactions.push().setValue(bookingsmodel)

                            openoutdoor.setValue(true)

                            val handler = Handler()
                            handler.postDelayed({
                                openoutdoor.setValue(false)
                            }, 32000)

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


    }




    fun getUserDetails(){

//     val saveuser=database.getReference("userDetails")


    }

    fun timeout1(): String{
        var timeout11 = "";
        val database = FirebaseDatabase.getInstance()
        val timeout1 = database.getReference("ParkingIOT/SLOT1/bookedby/timeout")

        timeout1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                timeout11 = dataSnapshot.getValue(Long::class.java).toString()
                if (timeout11 != null) {
                //    Toast.makeText(this@GetOutActivity, "$timeout1", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            override fun onCancelled(error: DatabaseError)
            {
                Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })
            Toast.makeText(this@GetOutActivity, "$timeout11", Toast.LENGTH_SHORT).show()
        return timeout11
    }

    fun calculateTime1(){
        val database = FirebaseDatabase.getInstance()
        val timein1 = database.getReference("ParkingIOT/SLOT1/bookedby/timein")
        val timeout1 = database.getReference("ParkingIOT/SLOT1/bookedby/timeout")
        var timeout1calc = "";
        var timein1calc = "";

        timeout1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val timeout1calc = dataSnapshot.getValue(Long::class.java)
                if (timeout1calc != null) {
                    Toast.makeText(this@GetOutActivity, "$timeout1calc", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            override fun onCancelled(error: DatabaseError)
            {
                Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })

        //
        timein1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val timein1calc = dataSnapshot.getValue(Long::class.java)
                if (timein1calc != null) {
                    Toast.makeText(this@GetOutActivity, "$timein1calc", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            override fun onCancelled(error: DatabaseError)
            {
                Toast.makeText(this@GetOutActivity, "Failed to read Value", Toast.LENGTH_SHORT).show()
            }
        })

        Toast.makeText(this@GetOutActivity, "Total time is $timein1calc /// and $timeout1calc", Toast.LENGTH_LONG).show()

    }

    fun timer(){

    }

}