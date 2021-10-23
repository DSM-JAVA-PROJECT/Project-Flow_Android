package com.example.project_flow_android.ui.flow

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.viewmodel.flow.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProjectFragment : BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm : ProjectViewModel by sharedViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goProjectImg.setOnClickListener(){
            goFlowPage()
        }

    }

    private fun goFlowPage(){
        val fragment = requireActivity().supportFragmentManager
        val fragmentManager = fragment.beginTransaction()
        fragmentManager.run {

        }
    }



}