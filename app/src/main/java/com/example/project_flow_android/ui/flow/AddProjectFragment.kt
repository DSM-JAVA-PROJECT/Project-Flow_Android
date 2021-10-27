package com.example.project_flow_android.ui.flow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseFragment
import com.example.project_flow_android.databinding.FragmentAddProjectBinding
import com.example.project_flow_android.viewmodel.flow.ProjectViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AddProjectFragment : BaseFragment<FragmentAddProjectBinding>(R.layout.fragment_add_project) {

    override val vm: ProjectViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        goFlowPage()

        return inflater.inflate(R.layout.fragment_add_project, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun goFlowPage() {
        val fragment = requireActivity().supportFragmentManager
        val fragmentManager = fragment.beginTransaction()
        fragmentManager.run {

        }
    }

    override fun observeEvent() {
        TODO("Not yet implemented")
    }


}