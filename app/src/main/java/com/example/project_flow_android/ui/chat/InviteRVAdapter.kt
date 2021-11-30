package com.example.project_flow_android.ui.chat

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.NonParticipateResponse
import kotlinx.android.synthetic.main.chat_create_user_item.view.*

class InviteRVAdapter(private val activity: Activity, private val items: NonParticipateResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(v: View, position: Int)
    }

    private var itemClickListener: InviteRVAdapter.OnItemClickListener? = null

    fun setOnItemClickListener(itemClickListener: InviteRVAdapter.OnItemClickListener){
        this.itemClickListener = itemClickListener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.chat_create_user_item, parent,false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.apply {
                bind(items.responses[position])
            }
        }
    }

    override fun getItemCount(): Int = items.responses.size

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item : NonParticipateResponse.NonUserList){
            view.create_user_item_name_tv.text = item.name
            if(item.profileImage != null){
                view.create_user_item_iv.clipToOutline = true
                Glide.with(activity).load(Uri.parse(item.profileImage)).into(view.create_user_item_iv)
            }

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.setOnClickListener{
                    itemClickListener?.onItemClick(itemView, position)
                }
            }
        }
    }
}