package com.example.project_flow_android.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.project_flow_android.ui.mypage.MyPageViewModel
import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mypageModule = module {
    viewModel { MyPageViewModel(get(),get()) }
}