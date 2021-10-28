package com.example.project_flow_android.di.module

import com.example.project_flow_android.viewmodel.flow.ProjectViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val aboutProjectmodule = module {

    viewModel{ProjectViewModel(get(),get(),get())}

}