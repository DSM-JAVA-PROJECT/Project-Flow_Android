package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectFragment : BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm: FlowViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()

    }

    override fun observeEvent() {
        vm.run {
            addProject.observe(viewLifecycleOwner,{
                //TODO 그냥 fragment 를 꺼야 하는지 아니면 다시 intent 를 해야 하는지
            })
        }
    }


}