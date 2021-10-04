package com.example.project_flow_android.di.module

import com.example.project_flow_android.viewmodel.chat.ChatViewModel
import com.example.project_flow_android.viewmodel.register.FinishSignViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val finishRegisterModule = module {
    viewModel { FinishSignViewModel(get()) }
}