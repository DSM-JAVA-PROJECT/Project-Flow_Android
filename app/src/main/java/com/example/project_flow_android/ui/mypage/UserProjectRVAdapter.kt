package com.example.project_flow_android.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectMypageItemBinding
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel

class UserProjectRVAdapter(private val viewModel: MyPageViewModel) :
    RecyclerView.Adapter<UserProjectRVAdapter.ProjectViewHolder>() {
    private var userProjectList = ArrayList<Projects>()

    inner class ProjectViewHolder(private val binding: UserProjectMypageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(position: Int) {
            binding.projectName = userProjectList[position].projectName
            binding.projectImage = userProjectList[position].logoImage
            binding.projectContent = userProjectList[position].startDate
            binding.projectEnd = userProjectList[position].endDate
            binding.vm = viewModel
            binding.notifyChange()
        }
    }

    fun setItem(projects: List<Projects>) {
        this.userProjectList = projects as ArrayList<Projects>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProjectViewHolder {
        val binding = UserProjectMypageItemBinding.inflate(LayoutInflater.from(parent.context))
        return ProjectViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProjectViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return userProjectList.size
    }


}