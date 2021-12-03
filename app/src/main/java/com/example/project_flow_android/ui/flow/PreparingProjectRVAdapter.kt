package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetProjectScheduleDetailResponse
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class PreparingProjectRVAdapter(
    private val viewModel: FlowViewModel
) :
    RecyclerView.Adapter<PreparingProjectRVAdapter.ProjectViewHolder>() {

    private var projectList = ArrayList<GetProjectScheduleDetailResponse>()

    lateinit var id: String

    inner class ProjectViewHolder(private val binding: UserProjectPlanItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetProjectScheduleDetailResponse, position: Int) {
            binding.scheduleContent = item.name
            binding.scheduleStartPeriod =
                "${item.startDate} ~ ${item.endDate}"
            binding.vm = viewModel
            binding.notifyChange()
            binding.userProjectCv.setOnClickListener {
                viewModel.getPlanId.value = item.planId
                viewModel.planclickFinish.value = item
            }
        }
    }

    fun setItem(projects: List<GetProjectScheduleDetailResponse>, id: String) {
        this.id = id
        this.projectList = projects as ArrayList<GetProjectScheduleDetailResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding =
            UserProjectPlanItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(projectList[position], position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }
}