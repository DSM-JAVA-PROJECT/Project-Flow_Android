package com.example.project_flow_android.ui.sign.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ActivityRegisterBinding
import com.example.project_flow_android.viewmodel.RegisterViewModel

class RegisterActivity(private val sharedPrefenceStorage: SharedPreferenceStorage): BaseActivity<ActivityRegisterBinding>(R.layout.activity_register) {

    override val vm: RegisterViewModel = RegisterViewModel(sharedPrefenceStorage)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
        binding.vm = vm

        nextPage()
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    fun nextPage(){
        vm.nextRegister()
        val intent = Intent(this,EmailVerifyActivity::class.java)
        startActivity(intent)
    }

    fun doLogin(){
        
    }





}