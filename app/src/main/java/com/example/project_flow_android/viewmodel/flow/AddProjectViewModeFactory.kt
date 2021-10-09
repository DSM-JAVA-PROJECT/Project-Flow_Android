package com.example.project_flow_android.viewmodel.flow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.project_flow_android.data.remote.FlowApilmpl

class AddProjectViewModeFactory(private val flowApiImpl: FlowApilmpl) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        modelClass.getConstructor(flowApiImpl::class.java)
            .newInstance(flowApiImpl)
}