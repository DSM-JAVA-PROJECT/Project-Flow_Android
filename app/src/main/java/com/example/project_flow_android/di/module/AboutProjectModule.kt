package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.FlowApilmpl
import com.example.project_flow_android.viewmodel.certification.CertificationViewModelFactory
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModeFactory
import org.koin.dsl.module

val aboutProjectmodule = module {
    single { FlowApilmpl() }
    single { AddProjectViewModeFactory(get()) }
}