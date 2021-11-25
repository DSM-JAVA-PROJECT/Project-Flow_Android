package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ItemFlowViewBinding
import com.example.project_flow_android.feature.GetMainInfoDetailResponse
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainFlowAdapter(private val viewModel: FlowViewModel) :RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder>(){
        private var userProjectList = ArrayList<GetMainInfoDetailResponse>()

        inner class MainFlowViewHolder(private val binding: ItemFlowViewBinding) :
            RecyclerView.ViewHolder(binding.root) {

            fun bind(position: Int) {
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
                val formatted = current.format(formatter)
                binding.today = formatted
                binding.projectLastDate = userProjectList[position].remainingDays
                binding.projectName = userProjectList[position].name
                binding.projectImage = userProjectList[position].logoIamge
                binding.vm = viewModel
                binding.notifyChange()
            }
        }

        fun setItem(projects: List<GetMainInfoDetailResponse>) {
            this.userProjectList = projects as ArrayList<GetMainInfoDetailResponse>
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFlowViewHolder {
            val binding = ItemFlowViewBinding.inflate(LayoutInflater.from(parent.context))
            return MainFlowViewHolder(binding)
        }

        override fun onBindViewHolder(holder: MainFlowViewHolder, position: Int) {
            holder.bind(position)
        }

        override fun getItemCount(): Int {
            return userProjectList.size
        }


    }