package com.example.project_flow_android.ui.sign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityRegisterBinding
import com.example.project_flow_android.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    override val vm: RegisterViewModel = RegisterViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}