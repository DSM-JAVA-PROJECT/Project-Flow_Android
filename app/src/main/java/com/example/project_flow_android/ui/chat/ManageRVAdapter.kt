package com.example.project_flow_android.ui.chat

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.RoomMemberResponse
import kotlinx.android.synthetic.main.manage_item.view.*

class ManageRVAdapter(private val items: RoomMemberResponse, private val activity: Activity) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(v: View, position: Int)
    }

    private var itemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.manage_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.apply {
                bind(items.responses[position])
            }
        }
    }

    override fun getItemCount() = items.responses.size

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: RoomMemberResponse.MemberResponse){
            if(item.profileImage != null){
                view.manage_item_profile_iv.clipToOutline = true
                Glide.with(activity).load(Uri.parse(item.profileImage)).into(view.manage_item_profile_iv)
            }
            view.manage_item_name_tv.text = item.name

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.setOnClickListener{
                    itemClickListener?.onItemClick(itemView, position)
                }
            }
        }
    }
}