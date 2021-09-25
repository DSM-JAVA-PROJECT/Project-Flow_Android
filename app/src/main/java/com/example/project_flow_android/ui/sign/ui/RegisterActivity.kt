package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityRegisterBinding
import com.example.project_flow_android.viewmodel.register.RegisterViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.android.ext.android.inject

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    private val vmFactory by inject<RegisterViewModelFactory>()
    override val vm: RegisterViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(RegisterViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.registerCheckTv.setOnClickListener() {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }


    override fun onBackPressed() {
        super.onBackPressed()

    }
}

