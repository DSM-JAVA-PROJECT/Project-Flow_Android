package com.example.project_flow_android.di.module

import android.app.Application
import com.example.project_flow_android.data.SharedPreferenceStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val mainModule = module {
    single { SharedPreferenceStorage(androidApplication()) }

}