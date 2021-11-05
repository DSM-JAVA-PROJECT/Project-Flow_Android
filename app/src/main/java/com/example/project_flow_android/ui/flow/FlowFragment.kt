package com.example.project_flow_android.ui.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow) {

    override val vm: FlowViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()

    }

    override fun observeEvent() {
        vm.run {
            getUserInfo()

        }
    }

}