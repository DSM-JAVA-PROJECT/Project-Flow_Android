package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class OningProjectRVAdapter(
    private val viewModel: FlowViewModel
) :
    RecyclerView.Adapter<OningProjectRVAdapter.ProjectViewHolder>() {

    private var projectList = ArrayList<GetMainInfoResponse.GetMainInfoDetailResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectOningItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetMainInfoResponse.GetMainInfoDetailResponse, position: Int) {
            binding.scheduleContent = item.before[position].name
            binding.scheduleStartPeriod =
                "${item.before[position].startDate} ~ ${item.before[position].endDate}"
            binding.vm = viewModel
            binding.notifyChange()
        }
    }

    fun setItem(projects: ArrayList<GetMainInfoResponse.GetMainInfoDetailResponse>) {
        this.projectList = projects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = UserProjectOningItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position], position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}