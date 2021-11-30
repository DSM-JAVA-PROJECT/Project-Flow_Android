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
import com.example.project_flow_android.ui.flow.dialog.FinishProjectDialog
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.util.DialogUtil
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel


class FlowFragment : BaseFragment<FragmentFlowBinding>(R.layout.fragment_flow) {

    override val vm: FlowViewModel by viewModel()
    private val addProject = AddProjectActivity()
    private val mainFlowViewPagerRVAdapter by lazy { MainFlowAdapter(vm) }
    private val preparingProjectRVAdapter by lazy { PreparingProjectRVAdapter(vm) }
    private val oningProjectRVAdapter by lazy { OningProjectRVAdapter(vm) }
    private val finishProjectRVAdapter by lazy { FinishProjectRVAdapter(vm) }


//    lateinit var binding_item: ItemFlowViewBinding
//    val dialogUtil = DialogUtil(requireActivity())

    private val finishProjectDialog by lazy {
        FinishProjectDialog(vm)
    }

    private fun finishProjectDialog() {
        finishProjectDialog.show(
            requireActivity().supportFragmentManager,
            "finishDialog"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showRV()
        finishSchdule()
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

    private fun finishSchdule() {
//        binding_item.button.setOnClickListener() {
//            val bottom = dialogUtil.showBottomSheet()
//            dialogUtil.showBottomSheet()
//            bottom.show()
//            finishProjectDialog()
//        }

    }

    private fun getProjectId(){
        val ProjectId = vm.getMainInfo.value!!
        ProjectId.projects[0].id
    }

    override fun observeEvent() {
        vm.run {
            getMainUserInfo()
            getProjectDetailInfo()
            vm.run {
                getMainInfo.observe(viewLifecycleOwner, {
                    mainFlowViewPagerRVAdapter.setItem(it.projects)
                    preparingProjectRVAdapter.setItem(it.projects)
                    oningProjectRVAdapter.setItem(it.projects)
                    finishProjectRVAdapter.setItem(it.projects)
                })
                emptyProject.observe(viewLifecycleOwner, {
                    if(emptyProject.value == true) {
                        binding.userName.isVisible
                        binding.emptyProjectTv.isVisible
                        binding.emptyProjectImg.isVisible
                        binding.nimProject.isVisible
                    }
                })
//                binding_item.button.setOnClickListener{
//                    getMainInfo.observe(viewLifecycleOwner,{
//                        getProjectId()
//                    })
//                }
            }
        }
    }
}