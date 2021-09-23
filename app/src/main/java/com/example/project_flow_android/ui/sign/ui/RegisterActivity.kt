package com.example.project_flow_android.ui.sign.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ActivityRegisterBinding
import com.example.project_flow_android.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding> (R.layout.activity_register){

    override val vm: RegisterViewModel by viewModels()


}
