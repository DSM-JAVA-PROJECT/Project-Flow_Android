package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityEmailVerifyBinding
import com.example.project_flow_android.viewmodel.certification.CertificationViewModel
import com.example.project_flow_android.viewmodel.certification.CertificationViewModelFactory
import org.koin.android.ext.android.inject

class CertificationActivity : BaseActivity<ActivityEmailVerifyBinding>(R.layout.activity_email_verify){

    private val vmFactory by inject<CertificationViewModelFactory>()
    override val vm: CertificationViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(CertificationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        successfulCertification()
        vm.run {
            secondPostCertificationCode.observe(this@CertificationActivity,{
                binding.comment3Tv.text = changeComment3.value
            })


        }
    }

    fun successfulCertification() {
        vm.run {
            successfulCertification.observe(this@CertificationActivity,{
                if(it) {
                    val intent =
                        Intent(this@CertificationActivity, EditPasswordActivity::class.java)
                    startActivity(intent)
                }
            })
        }
    }

    override fun onStart() {
        super.onStart()
        vm.run {
            postCertification()
            firstPostCertificationCode.observe(this@CertificationActivity, {
                binding.comment3Tv.text = changeComment3.value
            })
        }
    }
}