package com.example.project_flow_android.ui.calendar

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.feature.GitInfoResponse
import com.example.project_flow_android.ui.chat.InviteRVAdapter
import gun0912.tedimagepicker.util.ToastUtil.context

class GitRVAdapter: RecyclerView.Adapter<GitRVAdapter.ViewHolder>() {

    var list = mutableListOf<GitInfoResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_git_issue_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val txtName: TextView = view.findViewById(R.id.git_num)
        private val txtAge: TextView = view.findViewById(R.id.git_content)
        private val imgProfile: TextView = view.findViewById(R.id.git_user)

        fun bind(item: GitInfoResponse) {
            txtName.text = item.issueNumber
            txtAge.text = item.issueContent
            imgProfile.text = item.issueUser

        }
    }

}