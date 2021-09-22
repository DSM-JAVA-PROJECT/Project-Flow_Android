package com.example.project_flow_android.di

import com.example.project_flow_android.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    viewModel { LoginViewModel(get(),get()) }
}