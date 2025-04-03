package com.raaceinm.androidpracticals.activities


import android.app.Dialog
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.raaceinm.androidpracticals.R
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import java.util.Calendar

class TimePicker : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_time_picker)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top,
                systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun timePickerAddButtonOnClick(view: android.view.View) : String {
        var hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        var minutes = Calendar.getInstance().get(Calendar.MINUTE)
        var actualTime: String ?= null
        if ( minutes <= 9) {
            actualTime = "$hours:0$minutes"
        } else {
            actualTime = "$hours:$minutes"
        }
        var inputtedTime: String? = null
        Log.d("rrr","actualTime: $actualTime")

        CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            inputtedTime = showTimePicker()
            if (true) {
                if (actualTime == inputtedTime) {
                    findViewById<ImageButton>(R.id.TimePickerAddButton).setImageResource(R.drawable.check)
                    findViewById<TextView>(R.id.TimePickerTimeText).setTextColor(Color.GREEN)
                    findViewById<TextView>(R.id.TimePickerTimeText).text = inputtedTime
                } else {
                    findViewById<ImageButton>(R.id.TimePickerAddButton).setImageResource(R.drawable.add)
                    findViewById<TextView>(R.id.TimePickerTimeText).setTextColor(Color.RED)
                    findViewById<TextView>(R.id.TimePickerTimeText).text = inputtedTime
                }
            }
        }
        return inputtedTime.toString()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun showTimePicker() : String{
        var hours = 0
        var minutes = 0
        var time = ""

        val picker = MaterialTimePicker.Builder()
            .setTimeFormat(TimeFormat.CLOCK_24H)
            .setHour(12)
            .setMinute(1)
            .setTitleText("Select time")
            .build()
        picker.show(supportFragmentManager, "timePicker")

        return suspendCancellableCoroutine { continuation ->
            picker.addOnPositiveButtonClickListener {
                hours = picker.hour
                minutes = picker.minute
                if (0 <= minutes && minutes <= 9) {
                    time = "$hours:0$minutes"
                } else {
                    time = "$hours:$minutes"
                }
                Log.d("rrr", time)
                continuation.resume(time, null)
            }
        }
    }

    fun datePickerAddButtonOnClick(view: android.view.View) {
        var year = Calendar.getInstance().get(Calendar.YEAR)
        var month = Calendar.getInstance().get(Calendar.MONTH)+1
        var day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        var actualDate = "$month/$day/$year"
        var inputtedDate: String? = null

        CoroutineScope(kotlinx.coroutines.Dispatchers.Main).launch {
            inputtedDate = showDatePicker()
            Log.d("rrr","actualDate: $actualDate")
            Log.d("rrr", "inputtedDate is: $inputtedDate")
            if (true){
                if (actualDate == inputtedDate) {
                    findViewById<ImageButton>(R.id.DatePickerAddButton).setImageResource(R.drawable.check)
                    findViewById<TextView>(R.id.DatePickerTimeText).setTextColor(Color.GREEN)
                    findViewById<TextView>(R.id.DatePickerTimeText).text = inputtedDate
                } else {
                    findViewById<ImageButton>(R.id.DatePickerAddButton).setImageResource(R.drawable.add)
                    findViewById<TextView>(R.id.DatePickerTimeText).setTextColor(Color.RED)
                    findViewById<TextView>(R.id.DatePickerTimeText).text = inputtedDate
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun showDatePicker() : String? {
        var year = 0
        var month = 0
        var day = 0

        val picker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select date")
            .build()
        picker.show(supportFragmentManager, "datePicker")

        return suspendCancellableCoroutine { continuation ->
            picker.addOnPositiveButtonClickListener { _ ->
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.timeInMillis = picker.selection ?: 0L

                year = selectedCalendar.get(Calendar.YEAR)
                month = selectedCalendar.get(Calendar.MONTH) + 1
                day = selectedCalendar.get(Calendar.DAY_OF_MONTH)

                val date = "$month/$day/$year"
                continuation.resume(date, null)
            }
        }
    }

    fun alertMessage (view: View) {
        val time = findViewById<TextView>(R.id.DatePickerTimeText).getText().toString()
        val date = findViewById<TextView>(R.id.TimePickerTimeText).getText().toString()

        val alertBuilder: AlertDialog.Builder = AlertDialog.Builder(this)
        alertBuilder.setTitle("alert sample")
            .setIcon(R.drawable.check)
            .setMessage("you told that its $date. And you think that today is $time")
            .setPositiveButton("understood") { dialog, which -> dialog.dismiss() }
            .show()
    }

    fun dialogueMessage (view: View){
        val customDialog = Dialog(this)
        customDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        customDialog.setContentView(R.layout.custom_dialog)
        val button = customDialog.findViewById<Button>(R.id.customDialogButton)
        button.setOnClickListener {
            Snackbar.make(findViewById(android.R.id.content), "Custom dialog",
                Snackbar.LENGTH_SHORT).show()
            customDialog.dismiss()
        }
        customDialog.show()
    }
}