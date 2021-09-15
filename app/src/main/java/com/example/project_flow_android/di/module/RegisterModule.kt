package com.example.project_flow_android.di

import com.example.project_flow_android.viewmodel.LoginViewModel
import com.example.project_flow_android.viewmodel.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    viewModel { RegisterViewModel(get()) }
}