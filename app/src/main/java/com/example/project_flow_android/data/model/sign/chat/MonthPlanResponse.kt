package com.example.project_flow_android.data.model.sign.chat

data class MonthPlanResponse(
    val monthPlans: ArrayList<MonthResponse>
){
    data class MonthResponse(
        val startDate: String,
        val endDate: String,
        val isFinish: Boolean
    )
}
