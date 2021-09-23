package com.example.project_flow_android.di

import android.app.Application
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
                    registerModule
                )
            )
        }
    }
}