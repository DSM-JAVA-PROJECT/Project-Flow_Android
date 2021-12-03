package com.example.project_flow_android.ui.flow.dialog

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.project_flow_android.R
import com.example.project_flow_android.base.BaseDialog
import com.example.project_flow_android.databinding.DialogClosingPlanBinding
import com.example.project_flow_android.databinding.DialogFinishProjectBinding
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import kotlinx.android.synthetic.main.dialog_finish_project.*

class FinishPlanDialog(override val vm: FlowViewModel) :
    BaseDialog<DialogClosingPlanBinding>(R.layout.dialog_closing_plan) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            inputScheduleContentTv.text = vm?.planclickFinish?.value?.name
            closingScheduleBtn.setOnClickListener {
                vm?.finishPlan()
                dismiss()
            }

            //TODO 일정 지우기가 성공하면
            vm?.successPlanRemove?.observe(viewLifecycleOwner,{
                dismiss()
//                Toast.makeText(requireActivity(),"ddd",Toast.LENGTH_SHORT).show()
            })
        }
    }
}