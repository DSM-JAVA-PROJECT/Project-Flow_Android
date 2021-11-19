package com.example.project_flow_android.ui.sign.ui

import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityLoginBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.login.LoginViewModel
import com.example.project_flow_android.viewmodel.login.LoginViewModelFactory
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    private val vmFactory by inject<LoginViewModelFactory>()
    override val vm: LoginViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        successfulLogin()

        binding.oauthBtn.setOnClickListener {
            web_view.apply {
                getOauth()
            }
        }

    }

    private fun getOauth() {
        binding.webView.visibility
        val webView = findViewById<WebView>(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://18.210.203.222:8080/auth/oauth")
        binding.oauthBtn.isInvisible
        binding.registerBtn.isInvisible

    }

    private fun successfulLogin() {
        vm.run {
            successLogin.observe(this@LoginActivity, {
                if (it) {
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                    binding.comment2Tv.text = vm.changeComment.value
                }
                binding.comment2Tv.text = vm.changeComment.value
            })

        }
    }
}