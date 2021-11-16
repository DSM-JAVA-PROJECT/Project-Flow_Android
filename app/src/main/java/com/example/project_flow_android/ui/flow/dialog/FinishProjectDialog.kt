package com.example.project_flow_android.ui.flow.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogChangepasswordBinding
import com.example.project_flow_android.databinding.DialogFinishProjectBinding
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class FinishProjectDialog(override val vm: FlowViewModel) :
    BaseDialog<DialogFinishProjectBinding>(R.layout.dialog_finish_project) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cancleTv.setOnClickListener {
            vm.finishProject()
        }

        binding.confirmTv.setOnClickListener {
            dismiss()
        }

        vm.successRemove.observe(viewLifecycleOwner,{
            dismiss()
        })
    }
}