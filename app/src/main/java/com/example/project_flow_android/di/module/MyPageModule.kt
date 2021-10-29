package com.example.project_flow_android.di.module

import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mypageModule = module {
    viewModel { MyPageViewModel(get(),get()) }

    viewModel { ChangePasswordViewModel()}
}