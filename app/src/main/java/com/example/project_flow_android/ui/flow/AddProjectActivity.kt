package com.example.project_flow_android.ui.flow

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.model.sign.chat.ImageUpdateRequest
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.ActivityAddProjectBinding
import com.example.project_flow_android.util.GalleryHelper
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

    override val vm: AddProjectViewModel by viewModel()

    private val photoSelect =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                //Glide.with(requireContext()).load(it.data?.data).into(manage_project_iv)
                val data = it.data?.data!!.path
                val uri = it.data?.data!!
                fileUpload(data!!, uri)
            }
        }

    private fun fileUpload(filePath: String, uri: Uri) {
        val file = File(filePath)
        var inputStream: InputStream? = null
        try {
            inputStream = this.contentResolver.openInputStream(uri)
        } catch (e: Exception){
            e.printStackTrace()
        }
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
        val requestFile = byteArrayOutputStream.toByteArray().toRequestBody("image/jpg".toMediaTypeOrNull())
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)
        vm.addProject(body)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val galleryHelper = GalleryHelper(this)
        observeEvent()
        binding.getImg.setOnClickListener {
            galleryHelper.selectPhoto(photoSelect)
        }

    }

    private fun observeEvent() {
        vm.run {
            successAddProject.observe(this@AddProjectActivity, {
                val intent = Intent(this@AddProjectActivity, FlowFragment::class.java)
                startActivity(intent)
            })
            binding.goProjectImg.setOnClickListener {
                finish()
            }
        }
    }
}