package com.example.project_flow_android.ui.sign


import android.content.Intent
import android.os.Bundle
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityFinishSignBinding
import com.example.project_flow_android.viewmodel.register.FinishSignViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FinishSignActivity : BaseActivity<ActivityFinishSignBinding>(R.layout.activity_finish_sign) {

    override val vm: FinishSignViewModel by viewModel()

    override fun onStart() {
        super.onStart()

        vm.inputUserName()
    }

    override fun onBackPressed() {
        // non back press super.onBackPressed()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vm.run {
            finishRegister.observe(this@FinishSignActivity,{
                if(it) {
                    binding.usernameTv.text = vm.userName
                }
            })
        }

        binding.successRegisterBtn.setOnClickListener{
            val intent = Intent(this@FinishSignActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}


