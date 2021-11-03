package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.data.remote.sign.SignApiImpl
import com.example.project_flow_android.viewmodel.mypage.ChangePasswordViewModel
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel
import io.reactivex.rxjava3.core.Single
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mypageModule = module {
    viewModel { MyPageViewModel(get(), get()) }

    single { MyPageApiImpl() }

    viewModel { ChangePasswordViewModel(get(), get()) }
}