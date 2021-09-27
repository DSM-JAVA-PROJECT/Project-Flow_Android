package com.example.project_flow_android.di

import android.app.Application
import com.example.project_flow_android.di.module.certificationModule
import com.example.project_flow_android.di.module.mainModule
import com.example.project_flow_android.di.module.successfulModule

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
                    successfulModule
                )
            )
        }
    }
}