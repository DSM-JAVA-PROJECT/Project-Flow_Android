package com.example.project_flow_android.data.remote

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import androidx.loader.content.CursorLoader
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

fun Uri.toRealPath(context: Context): String {
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val loader = CursorLoader(context, this, proj, null, null, null)
    val cursor: Cursor = loader.loadInBackground()!!

    val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
    cursor.moveToFirst()

    val result =  cursor.getString(columnIndex)

    cursor.close()

    return result
}

fun File.toMultipartPart(): MultipartBody.Part {
    val fileBody = this.asRequestBody("image/jpeg".toMediaTypeOrNull())
    return MultipartBody.Part.createFormData("images", this.path, fileBody)
}