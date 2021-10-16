package com.example.project_flow_android.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.project_flow_android.R

class MainViewModel :ViewModel(){

    var activeFragment: Fragment? = null
    val tabSelectedItem = MutableLiveData(R.id.menu_flow_it)


}