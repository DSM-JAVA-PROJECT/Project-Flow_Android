package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class PreparingProjectRVAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<PreparingProjectRVAdapter.ProjectViewHolder>() {
    private var projectList = ArrayList<GetMainInfoDetailResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.scheduleContent = projectList[position].before[position].name
            binding.scheduleStartPeriod = projectList[position].before[position].startDate
            binding.scheduleEndPeriod = projectList[position].before[position].endDate
            binding.vm = viewModel
            binding.notifyChange()
        }
    }

    fun setItem(projects: List<Projects>) {
        this.projectList = projects as ArrayList<GetMainInfoDetailResponse>
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