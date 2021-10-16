package com.example.project_flow_android.di

import com.example.project_flow_android.viewmodel.register.RegisterViewModelFactory
import org.koin.dsl.module

val registerModule = module {
    single { RegisterViewModelFactory(get())}
}