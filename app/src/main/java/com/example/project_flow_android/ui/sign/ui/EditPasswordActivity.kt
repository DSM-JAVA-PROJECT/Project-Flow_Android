package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityEditPasswordBinding
import com.example.project_flow_android.viewmodel.register.RegisterViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.android.ext.android.inject

class EditPasswordActivity : BaseActivity<ActivityEditPasswordBinding>(R.layout.activity_edit_password){

    private val vmFactory by inject<RegisterViewModelFactory>()
    override val vm: RegisterViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        editPassword()
    }

    private fun editPassword(){
        vm.run {
            finishRegister.observe(this@EditPasswordActivity,{
                if(it){
                    val intent = Intent(this@EditPasswordActivity,CertificationActivity::class.java)
                    startActivity(intent)
                }
                binding.comment2Tv.text = changeComment2.value
            })

        }
    }
}