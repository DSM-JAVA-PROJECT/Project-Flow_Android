package com.example.project_flow_android.di.module

import com.example.project_flow_android.data.remote.flow.FlowApiImpl
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import com.example.project_flow_android.viewmodel.register.FinishSignViewModel
import io.reactivex.Single
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutProjectmodule = module {

    viewModel { FlowViewModel(get(),get()) }
    single { FlowApiImpl() }

}