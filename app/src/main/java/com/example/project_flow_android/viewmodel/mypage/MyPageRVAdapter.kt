package com.example.project_flow_android.viewmodel.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.databinding.UserProjectItemBinding
import com.example.project_flow_android.feature.GetUserInfoResponse

class MyPageRVAdapter(private val viewModel: MyPageViewModel) :
    RecyclerView.Adapter<MyPageRVAdapter.MyPageViewHolder>() {
    private val projectList = ArrayList<GetUserInfoResponse>()

    inner class MyPageViewHolder(private val binding: UserProjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.projectName = projectList[position].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageViewHolder {
        val binding = UserProjectItemBinding.inflate(LayoutInflater.from(parent.context))
        return MyPageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyPageViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int {
        return projectList.size
    }

}