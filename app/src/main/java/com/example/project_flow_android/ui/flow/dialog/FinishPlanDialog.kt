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
            inputScheduleContentTv.text = vm?.planItemContent?.value!!
            closingScheduleBtn.setOnClickListener {
                vm?.finishPlan()
                dismiss()
            }
            vm?.successPlanRemove?.observe(viewLifecycleOwner, {
                if (vm?.successPlanRemove?.value == true) {
                    Toast.makeText(requireActivity(), "일정 마감이 완료되었습니다", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
            })
        }
    }
}