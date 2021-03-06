package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.di.ProjectFlowApplication
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.feature.GetProjectScheduleDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class OningProjectRVAdapter(
    private val viewModel: FlowViewModel
) :
    RecyclerView.Adapter<OningProjectRVAdapter.ProjectViewHolder>() {
    private var projectList = ArrayList<GetProjectScheduleDetailResponse>()
    private val prefs = SharedPreferenceStorage(ProjectFlowApplication.context)

    lateinit var id: String

    inner class ProjectViewHolder(private val binding: UserProjectOningItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetProjectScheduleDetailResponse, position: Int) {
            binding.scheduleContent = item.name
            binding.scheduleStartPeriod =
                "${item.startDate} ~ ${item.endDate}"
            binding.vm = viewModel
            binding.notifyChange()
            binding.userProjectCv.setOnClickListener {
                viewModel.getProjectIdForPlan.value = item.planId
                prefs.savePlanId(item.planId,"planId")
                viewModel.planclickFinish.value = item
                viewModel.planItemContent.value = item.name
            }
        }
    }

    fun setItem(projects: List<GetProjectScheduleDetailResponse>, id: String) {
        this.id = id
        this.projectList = projects as ArrayList<GetProjectScheduleDetailResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OningProjectRVAdapter.ProjectViewHolder {
        val binding =
            UserProjectOningItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position], position)
    }

}