package com.example.project_flow_android.ui.flow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.databinding.ItemFlowViewBinding
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment() :BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow){

    override val vm: FlowViewModel by viewModel()
    private val addProject = AddProjectFragment()
    private val preparingProjectRVAdapter by lazy { PreparingProjectRVAdapter(vm) }
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

        val adapter = MainFlowAdapter(vm)
        val dotsIndicator = binding.dotsIndicator
        val viewPager = binding.mainView
        viewPager.adapter = adapter
        TabLayoutMediator(dotsIndicator, viewPager) {_, _ ->
        }.attach()

        rvSet(1)

        observeEvent()
        binding.addProjectBtn.setOnClickListener{
            (activity as MainActivity).addProject()
        }
    }

    private fun rvSet(position : Int){
        vm.run {
            getMainInfo.observe(viewLifecycleOwner,{
                mainFlowViewPagerRVAdapter.setItem(it.getProject)
            })
        }

    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            emptyProject.observe(viewLifecycleOwner, {
                binding.emptyProjectImg.visibility
                binding.emptyProjectTv.visibility
            })
            getProjectDetailInfo()


        }
    }

}