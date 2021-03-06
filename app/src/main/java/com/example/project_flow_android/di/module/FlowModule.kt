package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.data.remote.mypage.MyPageApiImpl
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val flowModule = module {
    viewModel { FlowViewModel(get(),get(),get()) }

    single { MyPageApiImpl() }
    single { FlowApiImpl() }
}