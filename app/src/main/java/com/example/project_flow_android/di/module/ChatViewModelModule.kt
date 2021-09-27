package com.example.project_flow_android.di

import com.example.project_flow_android.viewmodel.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val chatViewModelModule = module {
    viewModel { ChatViewModel() }
}