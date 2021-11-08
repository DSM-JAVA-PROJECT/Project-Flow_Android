package com.example.project_flow_android.ui.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.feature.GetUserInfoResponse
import com.example.project_flow_android.databinding.UserProjectMypageItemBinding
import com.example.project_flow_android.feature.Projects
import com.example.project_flow_android.viewmodel.mypage.MyPageViewModel

class UserProjectRVAdapter(private val viewModel: MyPageViewModel) :
    RecyclerView.Adapter<UserProjectRVAdapter.ProjectViewHolder>() {
    private var userProjectList = ArrayList<GetUserInfoResponse>()

    inner class ProjectViewHolder(private val binding: UserProjectMypageItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            //binding.projectName = userProjectList[position].projects[//TODO User]
            //binding.projectImage = userProjectList[position].//TODO 프로젝트 이미지
            //binding.projectContent = userProjectList[position].projects
            binding.vm = viewModel
            binding.executePendingBindings()
            binding.notifyChange()

        }

    }

    fun setItem(projects: List<GetUserInfoResponse>) {
        this.userProjectList = projects as ArrayList<GetUserInfoResponse>
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