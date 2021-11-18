package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.databinding.UserProjectProgressBinding
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow) {

    override val vm: FlowViewModel by viewModel()
    private val addProject = AddProjectFragment()

    private val finishProjectDialog by lazy {
        FinishProjectDialog(vm)
    }

    private fun showLogoutDialog() {
        finishProjectDialog.show(
            requireActivity().supportFragmentManager,
            "finishDialog"
        )
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeEvent()

    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            emptyProject.observe(viewLifecycleOwner, {
                binding.emptyProjectImg.visibility
                binding.emptyProjectTv.visibility
            })
            binding.addProjectBtn.setOnClickListener {
                (activity as MainActivity).addProject()
                //(activity as MainActivity).changeFragment(addProject)
            }
        }
    }

}