package com.example.project_flow_android.di

import com.example.project_flow_android.data.remote.LoginApiImpl
import com.example.project_flow_android.data.remote.SignApiImpl
import com.example.project_flow_android.viewmodel.LoginViewModel
import com.example.project_flow_android.viewmodel.register.LoginViewModelFactory
import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginModule = module {
    single { LoginApiImpl() }
    single { LoginViewModelFactory(get(),get()) }
}