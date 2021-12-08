package com.example.project_flow_android.ui.flow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.persistableBundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.data.SharedPreferenceStorage
import com.example.project_flow_android.databinding.ItemFlowViewBinding
import com.example.project_flow_android.di.ProjectFlowApplication
import com.example.project_flow_android.feature.GetMainInfoResponse
import com.example.project_flow_android.feature.GetProjectsId
import com.example.project_flow_android.util.HorizontalItemDecorator
import com.example.project_flow_android.util.VerticalItemDecorator
import com.example.project_flow_android.viewmodel.flow.FlowViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainFlowAdapter(private val viewModel: FlowViewModel) :
    RecyclerView.Adapter<MainFlowAdapter.MainFlowViewHolder>() {
    private var userProjectList = ArrayList<GetMainInfoResponse.GetMainInfoDetailResponse>()
    private val preparingProjectRVAdapter = PreparingProjectRVAdapter(viewModel)
    private val oningProjectRVAdapter = OningProjectRVAdapter(viewModel)
    private val finishProjectRVAdapter = FinishProjectRVAdapter(viewModel)
    private val prefs = SharedPreferenceStorage(ProjectFlowApplication.context)

    inner class MainFlowViewHolder(private val binding: ItemFlowViewBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: GetMainInfoResponse.GetMainInfoDetailResponse) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("MM월 dd일")
            val formatted = current.format(formatter)

            prefs.saveProjectId(item.id, "projectId")

            binding.today = formatted
            binding.projectLastDate = item.remainingDays
            binding.projectName = item.name
            binding.projectImage = item.logoImage
            binding.progressBar.setProgress(item.personalProgress.toInt())
            binding.progressBar2.setProgress(item.projectProgress.toInt())
            binding.personalProgress = "${item.personalProgress.toInt()}%"
            binding.teamProgress = "${item.projectProgress.toInt()}%"
            binding.userName = viewModel.getUserName.value
            binding.vm = viewModel

            binding.firstRv.adapter = preparingProjectRVAdapter
            binding.firstRv.layoutManager = LinearLayoutManager(binding.firstRv.context)
            binding.secondRv.layoutManager = LinearLayoutManager(binding.secondRv.context)
            binding.thirdRv.layoutManager = LinearLayoutManager(binding.thirdRv.context)
            binding.firstRv.addItemDecoration(VerticalItemDecorator(8))
            binding.firstRv.addItemDecoration(HorizontalItemDecorator(10))

            binding.secondRv.addItemDecoration(VerticalItemDecorator(8))
            binding.secondRv.addItemDecoration(HorizontalItemDecorator(10))

            binding.thirdRv.addItemDecoration(VerticalItemDecorator(8))
            binding.thirdRv.addItemDecoration(HorizontalItemDecorator(10))

            binding.secondRv.adapter = oningProjectRVAdapter
            binding.thirdRv.adapter = finishProjectRVAdapter

            preparingProjectRVAdapter.setItem(item.before, item.id)
            oningProjectRVAdapter.setItem(item.ongoing, item.id)
            finishProjectRVAdapter.setItem(item.after)

            binding.notifyChange()

            binding.button.setOnClickListener{
                viewModel.getProjectId.value = item.id
                viewModel.clickFinish.value = item
            }
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