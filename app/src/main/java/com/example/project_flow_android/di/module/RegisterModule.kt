package com.example.project_flow_android.di

import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.viewmodel.register.RegisterViewModel
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val registerModule = module {
    single { SignApiImpl() }
    single { RegisterViewModelFactory(get(),get()) }
}