package com.example.project_flow_android.di.module

import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.android.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val flowModule = module {
    viewModel { FlowViewModel() }
}