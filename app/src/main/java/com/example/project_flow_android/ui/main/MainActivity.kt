package com.example.project_flow_android.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseActivity
import com.example.project_flow_android.databinding.ActivityMainBinding
import com.example.project_flow_android.ui.chat.fragment.ChatFragment
import com.example.project_flow_android.ui.chat.fragment.ChatListFragment
import com.example.project_flow_android.ui.flow.FlowFragment
import com.example.project_flow_android.ui.mypage.MyPageFragment
import com.example.project_flow_android.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

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


    private val flowFragment = FlowFragment()
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
            add(R.id.main_container, chatFragment)
            add(R.id.main_container, myPageFragment)
        }.commit()
        resetFragment()
    }

    private fun resetFragment() {
        supportFragmentManager.beginTransaction().run {
            hide(flowFragment)
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