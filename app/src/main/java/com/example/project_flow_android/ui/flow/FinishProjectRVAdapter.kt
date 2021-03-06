package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectFinishItemBinding
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.feature.GetProjectScheduleDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class FinishProjectRVAdapter(
    private val viewModel: FlowViewModel
) :
    RecyclerView.Adapter<FinishProjectRVAdapter.ProjectViewHolder>() {

    private var projectList = ArrayList<GetProjectScheduleDetailResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectFinishItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetProjectScheduleDetailResponse, position: Int) {
            binding.scheduleContent = item.name
            binding.scheduleStartPeriod =
                "${item.startDate} ~ ${item.endDate}"
            binding.vm = viewModel
            binding.notifyChange()
        }
    }

    fun setItem(projects: List<GetProjectScheduleDetailResponse>) {
        this.projectList = projects as ArrayList<GetProjectScheduleDetailResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = UserProjectFinishItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProjectViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position], position)
    }
}