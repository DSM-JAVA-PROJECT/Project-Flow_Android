package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleRegistryOwner
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.ui.main.MainActivity
import com.example.project_flow_android.ui.mypage.VerticalItemDecorator
import com.example.project_flow_android.viewmodel.flow.AddProjectViewModel
import com.gun0912.tedpermission.TedPermission
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.security.Permission

class AddProjectFragment :
    BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm: AddProjectViewModel by viewModel()

    val PERM_STORAGE =9
    val PERM_CAMERA= 10

    val REO_CAMERA = 11


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun observeEvent() {
        vm.run {
            successAddProject.observe(viewLifecycleOwner, {
                (activity as MainActivity).backFragment()
            })
            binding.goProjectImg.setOnClickListener{
                (activity as MainActivity).backFragment()
            }
        }

    }


}