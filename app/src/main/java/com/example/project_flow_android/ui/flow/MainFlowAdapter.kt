package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ItemFlowViewBinding
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainFlowAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder>() {
    private var userProjectList = ArrayList<GetMainInfoResponse.GetMainInfoDetailResponse>()
    private val preparingProjectRVAdapter = PreparingProjectRVAdapter(viewModel)
    private val oningProjectRVAdapter = OningProjectRVAdapter(viewModel)
    private val finishProjectRVAdapter = FinishProjectRVAdapter(viewModel)

    inner class MainFlowViewHolder(private val binding: ItemFlowViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetMainInfoResponse.GetMainInfoDetailResponse) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
            val formatted = current.format(formatter)

            binding.today = formatted
            binding.projectLastDate = item.remainingDays
            binding.projectName = item.name
            binding.projectImage = item.logoImage
            binding.progressBar.setProgress(item.personalProgress)
            binding.progressBar2.setProgress(item.projectProgress)
            binding.personalProgress = "${item.personalProgress}%"
            binding.teamProgress = "${item.projectProgress}%"
            binding.userName = viewModel.getUserName.value!!
            binding.vm = viewModel

            binding.firstRv.adapter = preparingProjectRVAdapter
            binding.secondRv.adapter = oningProjectRVAdapter
            binding.thirdRv.adapter = finishProjectRVAdapter
            binding.notifyChange()
        }
    }

    fun setItem(projects: ArrayList<GetMainInfoResponse.GetMainInfoDetailResponse>) {
        this.userProjectList = projects
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFlowViewHolder {
        val binding = ItemFlowViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainFlowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFlowViewHolder, position: Int) {
        holder.bind(userProjectList[position])
    }

    override fun getItemCount(): Int {
        return userProjectList.size
    }


}