package com.example.project_flow_android.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityMainBinding
import com.example.project_flow_android.ui.calendar.CalendarFragment
import com.example.project_flow_android.ui.chat.fragment.ChatListFragment
import com.example.project_flow_android.ui.flow.DefaultProjectFragment
import com.example.project_flow_android.ui.mypage.MyPageFragment
import com.example.project_flow_android.ui.sign.LoginActivity
import com.example.project_flow_android.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_login.*

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val vm: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.mainBottomNavigation.setOnNavigationItemSelectedListener(itemSelectedListener)

        observerEvent()
    }

    override fun onBackPressed() {
        // TODO: 2021/10/06  super.onBackPressed()
    }

    override fun onStart() {
        super.onStart()
        initFragment()
    }

    fun startLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }


    private val flowFragment = DefaultProjectFragment()
    private val calendarFragment = CalendarFragment()
    private val chatFragment = ChatListFragment()
    private val myPageFragment = MyPageFragment()

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().hide(vm.activeFragment ?: flowFragment)
            .show(fragment).commit()
        vm.activeFragment = fragment
    }

    private val itemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            vm.tabSelectedItem.value = item.itemId
            true
        }

    private fun initFragment() {
        supportFragmentManager.beginTransaction().run {
            add(R.id.main_container, flowFragment)
            add(R.id.main_container,calendarFragment)
            add(R.id.main_container, chatFragment)
            add(R.id.main_container, myPageFragment)
        }.commit()
        resetFragment()
    }

    private fun resetFragment() {
        supportFragmentManager.beginTransaction().run {
            hide(flowFragment)
            hide(calendarFragment)
            hide(chatFragment)
            hide(myPageFragment)
        }.commit()
        changeFragment(vm.activeFragment ?: flowFragment)
    }

    private fun observerEvent() {
        vm.run {
            tabSelectedItem.observe(this@MainActivity, { id ->
                when (id) {
                    R.id.menu_flow_it -> {
                        changeFragment(flowFragment)
                    }
                    R.id.menu_calendar_it -> {
                        changeFragment(calendarFragment)
                    }
                    R.id.menu_chat_it -> {
                        changeFragment(chatFragment)
                    }
                    R.id.menu_mypage_it -> {
                        changeFragment(myPageFragment)
                    }
                }
            })
        }
    }
}