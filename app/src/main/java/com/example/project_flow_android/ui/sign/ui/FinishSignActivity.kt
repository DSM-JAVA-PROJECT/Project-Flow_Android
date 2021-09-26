package com.example.project_flow_android.ui.sign.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityEditPasswordBinding
import com.example.project_flow_android.databinding.ActivityFinishSignBinding
import com.example.project_flow_android.viewmodel.register.FinishSignViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishSignActivity : BaseActivity<ActivityFinishSignBinding>(R.layout.activity_finish_sign) {

    override val vm: FinishSignViewModel by viewModel()

}


