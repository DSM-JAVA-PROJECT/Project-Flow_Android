package com.example.project_flow_android.di.module

import android.app.Application
<<<<<<< HEAD:app/src/main/java/com/example/project_flow_android/di/ProjectFlowApplication.kt
import com.example.project_flow_android.di.module.*
=======
import com.example.project_flow_android.di.loginModule
import com.example.project_flow_android.di.registerModule
>>>>>>> chat:app/src/main/java/com/example/project_flow_android/di/module/ProjectFlowApplication.kt

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
                    aboutProjectmodule
                )
            )
        }
    }
}