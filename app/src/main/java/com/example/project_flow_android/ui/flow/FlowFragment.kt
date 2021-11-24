package com.example.project_flow_android.ui.flow

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.ui.mypage.dialog.ChangePasswordDialog
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment :BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow){


    override val vm: FlowViewModel by viewModel()
    private val addProject = AddProjectFragment()
    private val preparingProjectRVAdapter by lazy { PreparingProjectRVAdapter(vm) }

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
        observeEvent()

    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            emptyProject.observe(viewLifecycleOwner, {
                binding.emptyProjectImg.visibility
                binding.emptyProjectTv.visibility
            })
            //fragment -> activity
            binding.addProjectBtn.setOnClickListener {
                (activity as MainActivity).goAddProject()
            }
            getProjectInfo()
            getProjectDetailInfo(1)

                //item 에서 처리 해야 하는 것들
//            binding.button.setOnClickListener{
//                finishProjectDialog()
//            }
//            binding.firstRv.adapter = preparingProjectRVAdapter
//            vm.inputDialogProjectName()
//            val percent : Int = vm.personalProgress.value.toString()
//            val teamPercent = vm.projectProgress.value.toString()
//            binding.progressBar.setProgress(percent.toInt())
//            binding.progressBar2.setProgress(teamPercent.toInt())

        }
    }

}