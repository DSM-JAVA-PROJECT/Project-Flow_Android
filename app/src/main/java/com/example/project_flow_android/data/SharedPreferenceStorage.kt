package com.example.project_flow_android.data

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceStorage(private val context: Context) {
    private var pref: SharedPreferences? = null

    fun getInfo(content: String?): String {
        if (pref == null) pref = context.getSharedPreferences("content", Context.MODE_PRIVATE)
        return if (content == "access_token") {
            "Bearer " + pref!!.getString(content, "")
        } else
            pref!!.getString(content, "").toString()
    }

    fun saveInfo(info: String, content: String) {
        if (pref == null) pref = context.getSharedPreferences("content", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putString(content, info)
        editor.apply()
    }

    fun saveInfo(info: Int, content: String) {
        if (pref == null) pref = context.getSharedPreferences("content", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = pref!!.edit()
        editor.putInt(content, info)
        editor.apply()
    }

    fun getIntInfo(content: String?):Int{
        if (pref == null) pref = context.getSharedPreferences("content", Context.MODE_PRIVATE)
        return pref!!.getInt(content, 0)
    }

    fun clearAll() {
        if (pref == null) pref = context.getSharedPreferences("content", Context.MODE_PRIVATE)
        pref!!.edit().clear().apply()
    }
}