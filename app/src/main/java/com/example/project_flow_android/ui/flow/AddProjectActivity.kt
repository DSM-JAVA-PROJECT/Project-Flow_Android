package com.example.project_flow_android.ui.flow

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.ActivityAddProjectBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding>(R.layout.activity_add_project) {

    override val vm : AddProjectViewModel by viewModel()

    private fun getImage() {
        TedRxImagePicker.with(this)
            .start()
            .subscribe({ uri ->
                val imagePath = uri.toRealPath(this)
                vm.imagePath = imagePath
                binding.getImg.setImageURI(uri)
            }, Throwable::printStackTrace)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()
    }

    fun observeEvent() {
        vm.run {
            successAddProject.observe(this@AddProjectActivity, {
//                val intent = Intent(this@AddProjectActivity, MainActivity::class.java)
//                startActivity(intent)
            })
            binding.getImg.setOnClickListener {
                getImage()
            }
        }
    }
}