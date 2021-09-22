package com.example.project_flow_android.di

import com.example.project_flow_android.network.StompClient
import org.koin.dsl.module

val socketModule = module {
    single { StompClient() }
}