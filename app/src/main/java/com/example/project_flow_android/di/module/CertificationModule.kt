package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.CertificationApiImpl
import com.example.project_flow_android.data.remote.LoginApiImpl
import com.example.project_flow_android.viewmodel.register.CertificationViewModelFactory
import com.example.project_flow_android.viewmodel.register.LoginViewModelFactory
import org.koin.dsl.module

val certificationModule = module {
    single { CertificationApiImpl() }
    single { CertificationViewModelFactory(get(),get()) }
}