package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectOningItemBinding
import com.example.project_flow_android.databinding.UserProjectPlanItemBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.flow.FlowViewModel

class OningProjectRVAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<OningProjectRVAdapter.ProjectViewHolder>() {
        private var projectList = ArrayList<GetMainInfoDetailResponse>()

        inner class ProjectViewHolder(private val binding: UserProjectOningItemBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(position: Int) {
                binding.scheduleContent = projectList[position].ongoing[position].name
                binding.scheduleStartPeriod = projectList[position].ongoing[position].startDate
                binding.scheduleEndPeriod = projectList[position].ongoing[position].endDate
                binding.vm = viewModel
                binding.notifyChange()
            }
        }

        fun setItem(projects: List<Projects>) {
            this.projectList = projects as ArrayList<GetMainInfoDetailResponse>
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
            val binding = UserProjectOningItemBinding.inflate(LayoutInflater.from(parent.context))
            return ProjectViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return projectList.size
        }
}