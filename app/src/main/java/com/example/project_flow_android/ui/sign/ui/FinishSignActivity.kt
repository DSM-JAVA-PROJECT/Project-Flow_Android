package com.example.project_flow_android.ui.sign.ui


import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityFinishSignBinding
import com.example.project_flow_android.viewmodel.register.FinishSignViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishSignActivity : BaseActivity<ActivityFinishSignBinding>(R.layout.activity_finish_sign) {

    override val vm: FinishSignViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        vm.run {
            finishRegister.observe(this@FinishSignActivity,{
                if(it) {
                    vm.inputUserName()
                    binding.textView.text = vm.userName
                }
            })
        }

    }

}


