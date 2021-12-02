package com.example.project_flow_android.util

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.EditText
import com.example.project_flow_android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import org.aviran.cookiebar2.CookieBar
import java.text.SimpleDateFormat
import java.util.*

class DialogUtil(private val activity: Activity) {
    fun showDatePicker(editText: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(activity, R.style.DatePickerStyle,
            { view, year, month, dayOfMonth ->
                if(dayOfMonth < 10){
                    editText.setText("${year}년 ${month+1}월 0${dayOfMonth}일")
                } else {
                    editText.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
                }
            }, year, month, day)
        datePicker.show()
    }

    fun showBottomSheet() : BottomSheetDialog{
        val bottom = BottomSheetDialog(activity)
        bottom.setContentView(R.layout.add_schedule_bottom)
        return bottom
    }

    fun cookieBarBuilder(
        title: Int,
        message: String?,
        res_message: Int?,
        backgroundColor: Int,
    ) {
        if (message != null) {
            CookieBar.build(activity)
                .setTitle(title)
                .setMessage(message)
                .setTitleColor(R.color.white)
                .setBackgroundColor(backgroundColor)
                .show()
        } else {
            CookieBar.build(activity)
                .setTitle(title)
                .setMessage(res_message!!)
                .setTitleColor(R.color.white)
                .setBackgroundColor(backgroundColor)
                .show()
        }
    }
}