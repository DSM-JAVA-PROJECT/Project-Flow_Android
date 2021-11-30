package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.data.remote.sign.LoginApiImpl
import com.example.project_flow_android.viewmodel.calendar.CalendarViewModel
import com.example.project_flow_android.viewmodel.login.LoginViewModelFactory
import org.koin.dsl.module

val gitinfoModule = module {
    single { MyPageApiImpl() }
    single { CalendarViewModel(get()) }
}