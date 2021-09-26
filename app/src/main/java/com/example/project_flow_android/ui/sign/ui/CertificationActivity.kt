package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityEmailVerifyBinding
import com.example.project_flow_android.viewmodel.CertificationViewModel
import com.example.project_flow_android.viewmodel.LoginViewModel
import com.example.project_flow_android.viewmodel.register.CertificationViewModelFactory
import com.example.project_flow_android.viewmodel.register.LoginViewModelFactory
import org.koin.android.ext.android.inject

class CertificationActivity : BaseActivity<ActivityEmailVerifyBinding>(R.layout.activity_email_verify){

    private val vmFactory by inject<CertificationViewModelFactory>()
    override val vm: CertificationViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(CertificationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.loginBtn.setOnClickListener{
            val intent = Intent(this,EditPasswordActivity::class.java)
            startActivity(intent)
        }
    }

}