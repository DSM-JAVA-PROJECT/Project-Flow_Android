package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentDefaultProjectBinding
import com.example.project_flow_android.viewmodel.flow.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DefaultProjectFragment :
    BaseFragment<FragmentDefaultProjectBinding>(R.layout.fragment_default_project) {
    override val vm: ProjectViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_default_project, container, false)
    }

    override fun observeEvent() {
        TODO("Not yet implemented")
    }

}