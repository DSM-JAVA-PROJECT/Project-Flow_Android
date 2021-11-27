package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectFinishItemBinding
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class FinishProjectRVAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<FinishProjectRVAdapter.ProjectViewHolder>() {
    private var projectList = ArrayList<GetMainInfoDetailResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectFinishItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.scheduleContent = projectList[position].after[position].name
            binding.scheduleStartPeriod = projectList[position].after[position].startDate
            binding.scheduleEndPeriod = projectList[position].after[position].endDate
            binding.vm = viewModel
            binding.notifyChange()
        }
    }

    fun setItem(projects: List<Projects>) {
        this.projectList = projects as ArrayList<GetMainInfoDetailResponse>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FinishProjectRVAdapter.ProjectViewHolder {
        val binding = UserProjectFinishItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProjectViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(position)
    }
}