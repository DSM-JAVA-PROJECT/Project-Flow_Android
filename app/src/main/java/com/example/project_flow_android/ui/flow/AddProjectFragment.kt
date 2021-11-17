package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.View
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm: AddProjectViewModel by viewModel()

    override fun observeEvent() {
        vm.run {
            successAddProject.observe(viewLifecycleOwner, {
                (activity as MainActivity).changeFragment(FlowFragment())
            })
        }

    }


}