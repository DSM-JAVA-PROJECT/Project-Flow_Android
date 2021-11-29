package com.example.project_flow_android.feature

data class GetMainInfoResponse(val projects : ArrayList<GetMainInfoDetailResponse>){
    data class GetMainInfoDetailResponse(
        val id: String,
        val name: String,
        val logoImage: String,
        val startDate: String,
        val endDate: String,
        val personalProgress: String,
        val projectProgress: String,
        val remainingDays : String,
        val before : List<GetProjectScheduleDetailResponse>,
        val ongoing : List<GetProjectScheduleDetailResponse>,
        val after : List<GetProjectScheduleDetailResponse>
    )
}

