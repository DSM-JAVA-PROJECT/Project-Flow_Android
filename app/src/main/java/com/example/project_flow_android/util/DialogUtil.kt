package com.example.project_flow_android.util

import android.app.DatePickerDialog
import android.content.Context
import android.widget.EditText
import com.example.project_flow_android.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*

class DialogUtil(private val context: Context) {
    fun showDatePicker(editText: EditText){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(context, R.style.DatePickerStyle,
            { view, year, month, dayOfMonth ->
                editText.setText("${year}년 ${month+1}월 ${dayOfMonth}일")
            }, year, month, day)
        datePicker.show()
    }

    fun showBottomSheet() : BottomSheetDialog{
        val bottom = BottomSheetDialog(context)
        bottom.setContentView(R.layout.add_schedule_bottom)
        return bottom
    }
}