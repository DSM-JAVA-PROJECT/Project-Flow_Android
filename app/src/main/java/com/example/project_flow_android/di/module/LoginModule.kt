package com.example.project_flow_android.di

import com.example.project_flow_android.data.remote.sign.LoginApiImpl
import com.example.project_flow_android.viewmodel.login.LoginViewModelFactory
import org.koin.dsl.module

val loginModule = module {
    single { LoginApiImpl() }
    single { LoginViewModelFactory(get(),get()) }
}