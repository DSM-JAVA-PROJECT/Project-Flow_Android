package com.example.project_flow_android.ui.sign.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityDashBoradBinding

class DashBoardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_borad)

        Handler().postDelayed({
            val dashboardIntent = Intent(this,RegisterActivity::class.java)
            startActivity(dashboardIntent)
        },2000)
    }
}