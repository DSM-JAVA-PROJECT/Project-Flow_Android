package com.example.project_flow_android.ui.flow

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.ActivityAddProjectBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModel
import gun0912.tedimagepicker.builder.TedRxImagePicker
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectActivity : BaseActivity<ActivityAddProjectBinding>(R.layout.activity_add_project) {

    override val vm: AddProjectViewModel by viewModel()

    private fun getImage() {
        TedRxImagePicker.with(this)
            .start()
            .subscribe({ uri ->
                val imagePath = uri.toRealPath(this)
                vm.imagePath = imagePath
                binding.getImg.setImageURI(uri)
                vm.getImage2()
            }, Throwable::printStackTrace)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeEvent()

    }

    private fun observeEvent() {
        vm.run {
            successAddProject.observe(this@AddProjectActivity, {
                if (successAddProject.value == true) {
                    Toast.makeText(this@AddProjectActivity,"프로젝트 생성에 성공하였습니다",Toast.LENGTH_SHORT).show()
                    finish()
            }
        })
        binding.getImg.setOnClickListener {
            getImage()
        }
        binding.goProjectImg.setOnClickListener {
           finish()
        }
    }
}
}