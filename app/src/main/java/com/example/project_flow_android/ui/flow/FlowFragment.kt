package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow) {

    override val vm: FlowViewModel by viewModel()

    val secondfragment = AddProjectFragment()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeEvent()

    }


    fun changeFragment() {
        childFragmentManager.beginTransaction()
            .replace(R.id.fragment, secondfragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()
    }

    fun addProject() {
        binding.addprojectBtn.setOnClickListener {
            changeFragment()
        }
    }

    override fun observeEvent() {
        vm.run {
            getUserInfo()
            addProject()
        }
    }

}