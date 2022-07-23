package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvselectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val selectDate : Button = findViewById<Button>(R.id.buttonDatePicker)
        tvselectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        selectDate.setOnClickListener{
            clickDatePicker()

        }
    }
    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {view,selected_year,selected_month ,selected_dayOfMonth ->
//                Toast.makeText(this,
//                    "DatePicker works!  ",
//                    Toast.LENGTH_LONG).show()
                val selectedDate =  "$selected_dayOfMonth/${selected_month+1}/$selected_year"
                tvselectedDate?.text =selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyy",Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time / 60000
                    val currentDate= sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes= currentDate.time / 60000
                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes
                        tvAgeInMinutes?.text = differenceInMinutes.toString()
                        val differenceInHours = differenceInMinutes / 60
                        val differenceInDays = differenceInHours / 24
                        tvAgeInHours?.text = differenceInHours.toString()
                        tvAgeInDays?.text = differenceInDays.toString()
                    }

                }

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis()  - 86400000
        dpd.show()
//        Toast.makeText(this,
//            "btnDatePicker pressed",
//            Toast.LENGTH_LONG).show()
    }
}