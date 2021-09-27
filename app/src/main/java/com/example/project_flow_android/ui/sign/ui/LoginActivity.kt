package com.example.project_flow_android.ui.sign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityLoginBinding
import com.example.project_flow_android.viewmodel.LoginViewModel
import com.example.project_flow_android.viewmodel.register.LoginViewModelFactory
import com.example.project_flow_android.viewmodel.register.RegisterViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login){

    private val vmFactory by inject<LoginViewModelFactory>()
    override val vm: LoginViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)
    }

}