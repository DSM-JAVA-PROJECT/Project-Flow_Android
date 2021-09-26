package com.example.project_flow_android.util

import android.app.Application
import com.example.project_flow_android.di.chatViewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TestApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@TestApplication)
            modules(listOf(chatViewModelModule))
        }
    }
}