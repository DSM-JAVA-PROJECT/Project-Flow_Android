package com.example.project_flow_android.ui.sign.ui

import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityLoginBinding
import com.example.project_flow_android.viewmodel.login.LoginViewModel
import com.example.project_flow_android.viewmodel.login.LoginViewModelFactory
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

    private val vmFactory by inject<LoginViewModelFactory>()
    override val vm: LoginViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)
    }

}