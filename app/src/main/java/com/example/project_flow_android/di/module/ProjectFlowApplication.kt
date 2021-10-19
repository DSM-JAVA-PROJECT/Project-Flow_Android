package com.example.project_flow_android.di.module

import android.app.Application
import com.example.project_flow_android.di.chatViewModelModule
import com.example.project_flow_android.di.loginModule
import com.example.project_flow_android.di.registerModule

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProjectFlowApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ProjectFlowApplication)
            modules(
                listOf(
                    loginModule,
                    registerModule,
                    mainModule,
                    certificationModule,
                    successfulModule,
                    chatViewModelModule
                )
            )
        }
    }
}