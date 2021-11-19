package com.example.project_flow_android.ui.chat

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.UserProfileResponse
import kotlinx.android.synthetic.main.user_info_item.view.*

class UserProjectRVAdapter(private val items: UserProfileResponse, private val activity: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView =
            LayoutInflater.from(parent.context).inflate(R.layout.user_info_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.apply {
                bind(items.projectResponses[position])
            }
        }
    }

    override fun getItemCount() = items.projectResponses.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: UserProfileResponse.ProjectResponse) {
            if (item.projectImage != null) {
                view.user_info_item_profile_iv.clipToOutline = true
                Glide.with(activity).load(Uri.parse(item.projectImage))
                    .into(view.user_info_item_profile_iv)
            }
            view.user_info_item_name_tv.text = item.projectName
            view.user_info_term_tv.text = "${item.projectStartDate} ~ ${item.projectEndDate}"
            if(item.finished){
                view.user_info_success_tv.text = activity.getString(R.string.user_info_finish)
            } else {
                view.user_info_success_tv.text = activity.getString(R.string.user_info_fail)
                view.user_info_success_tv.background = activity.getDrawable(R.drawable.user_info_fail_radius)
            }
        }
    }
}