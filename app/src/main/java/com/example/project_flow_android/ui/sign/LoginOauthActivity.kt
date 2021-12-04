package com.example.project_flow_android.ui.sign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityLoginOauthBinding
import com.example.project_flow_android.viewmodel.login.LoginViewModel
import com.example.project_flow_android.viewmodel.login.LoginViewModelFactory
import org.koin.android.ext.android.inject

class LoginOauthActivity : BaseActivity<ActivityLoginOauthBinding>(R.layout.activity_login_oauth) {

    private val vmFactory by inject<LoginViewModelFactory>()
    override val vm: LoginViewModel by lazy {
        ViewModelProvider(this, vmFactory).get(LoginViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_oauth)

        getOauth()

    }


    private fun getOauth() {
        binding.webView.visibility
        val webView = findViewById<WebView>(R.id.web_view)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("http://18.210.203.222:8080/auth/oauth")
        lazy {
            vm.doOauth()
        }
    }
}
