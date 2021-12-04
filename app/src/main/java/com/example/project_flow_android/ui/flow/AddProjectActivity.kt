package com.example.project_flow_android.ui.flow

import android.content.Intent
import android.os.Bundle
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.remote.toRealPath
import com.example.project_flow_android.databinding.ActivityAddProjectBinding
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
                    val intent = Intent(this@AddProjectActivity, FlowFragment::class.java)
                    startActivity(intent)
            }
        })
        binding.getImg.setOnClickListener {
            getImage()
        }
        binding.goProjectImg.setOnClickListener {
           //TODO
        }
    }
}
}