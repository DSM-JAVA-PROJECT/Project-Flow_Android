package com.example.project_flow_android.util

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

class KeyboardUtil(context: Context) {
    private val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    fun hideKeyboard(editText: EditText){
        imm.hideSoftInputFromWindow(editText.windowToken, 0)
    }

    fun showKeyboard(){
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}