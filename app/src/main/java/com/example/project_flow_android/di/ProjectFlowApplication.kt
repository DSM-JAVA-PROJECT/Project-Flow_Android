package com.example.project_flow_android.di

import android.app.Application
import android.content.Context
import com.example.project_flow_android.di.module.*

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
                    chatViewModelModule,
                    finishRegisterModule,
                    aboutProjectmodule,
                    mypageModule,
                    flowModule,
                    gitinfoModule
                )
            )
        }
        context = applicationContext
    }
    companion object{
        lateinit var context: Context
            private set
    }
}