package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.View
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewbinding.ViewBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ActivityAddProjectBinding.inflate
import com.example.project_flow_android.databinding.FragmentFlowBinding
import com.example.project_flow_android.databinding.ItemFlowViewBinding
import com.example.project_flow_android.ui.flow.dialog.FinishPlanDialog
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow) {

    override val vm: FlowViewModel by viewModel()
    private val mainFlowViewPagerRVAdapter by lazy { MainFlowAdapter(vm) }

    private val finishProjectDialog by lazy {
        FinishProjectDialog(vm)
    }

    private val finishPlanDialog by  lazy {
        FinishPlanDialog(vm)
    }
//
//    val dialogUtil by lazy {
//        DialogUtil(requireActivity())
//    }


    private fun finishProjectDialog() {
        finishProjectDialog.show(
            requireActivity().supportFragmentManager,
            "finishDialog"
        )
    }

    private fun finishPlanDialog() {
        finishProjectDialog.show(
            requireActivity().supportFragmentManager,
            "finishPlanDialog"
        )
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showRV()
        observeEvent()
        goAddProject()
    }


    private fun goAddProject() {
        binding.addProjectBtn.setOnClickListener {
            (activity as MainActivity).addProject()
        }
    }

    private fun showRV() {
        binding.mainView.adapter = mainFlowViewPagerRVAdapter
        binding.mainView.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val dotsIndicator = binding.dotsIndicator
        TabLayoutMediator(dotsIndicator, binding.mainView) { _, _ ->
        }.attach()
    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            getProjectDetailInfo()
            getMainInfo.observe(viewLifecycleOwner, {
                mainFlowViewPagerRVAdapter.setItem(it.projects)
            })
            fullProject.observe(viewLifecycleOwner, {
                if (fullProject.value == true) {
                    binding.isLoading = true
                }
            })
            clickFinish.observe(viewLifecycleOwner, {
                finishProjectDialog()
            })
            planclickFinish.observe(viewLifecycleOwner, {
                finishPlanDialog()
//                val bottom = dialogUtil.showScheduleBottomSheet()
//                bottom.show()
            })

        }
    }
}