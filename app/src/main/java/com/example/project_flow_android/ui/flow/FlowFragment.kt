package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.viewpager2.widget.ViewPager2
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment() :BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow){

    override val vm: FlowViewModel by viewModel()
    private val addProject = AddProjectFragment()
    private val mainFlowViewPagerRVAdapter by lazy { MainFlowAdapter(vm) }

    private val finishProjectDialog by lazy {
        FinishProjectDialog(vm)
    }

    private fun finishProjectDialog() {
        finishProjectDialog.show(
            requireActivity().supportFragmentManager,
            "finishDialog"
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainView.adapter = mainFlowViewPagerRVAdapter
        binding.mainView.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val dotsIndicator = binding.dotsIndicator

//        TabLayoutMediator(dotsIndicator, viewPager) {_, _ ->
//        }.attach()
        observeEvent()
        binding.addProjectBtn.setOnClickListener{
            (activity as MainActivity).addProject()
        }
    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            getProjectDetailInfo()
            vm.run {
                getMainInfo.observe(viewLifecycleOwner,{
                    mainFlowViewPagerRVAdapter.setItem(it.getProject)
                })
            }

        }
    }

}