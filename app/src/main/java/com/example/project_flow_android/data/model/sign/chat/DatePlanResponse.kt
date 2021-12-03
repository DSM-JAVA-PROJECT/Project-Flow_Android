package com.example.project_flow_android.data.model.sign.chat

data class DatePlanResponse(
    val planDetails: ArrayList<DateResponse>
){
    data class DateResponse(
        val id: String,
        val name: String,
        val isFinish: Boolean
    )
}
