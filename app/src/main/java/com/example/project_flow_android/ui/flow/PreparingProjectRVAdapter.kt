package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.GetProjectScheduleDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class PreparingProjectRVAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<PreparingProjectRVAdapter.ProjectViewHolder>() {
    private var projectList = ArrayList<GetProjectScheduleDetailResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.scheduleContent = projectList[position].name
            binding.scheduleStartPeriod = "${projectList[position].startDate} ~ ${projectList[position].endDate}"
            binding.vm = viewModel
            binding.notifyChange()
        }
    }
    fun setItem(projects: List<Projects>) {
        this.projectList = projects as ArrayList<GetProjectScheduleDetailResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = UserProjectPlanItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}