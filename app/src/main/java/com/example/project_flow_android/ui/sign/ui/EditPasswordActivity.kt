package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
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

        binding.registerBtn.setOnClickListener{
            val intent = Intent(this,FinishSignActivity::class.java)
            startActivity(intent)
        }
    }


}