package com.example.project_flow_android.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.data.chat.ProjectMemberResponse
import kotlinx.android.synthetic.main.chat_create_user_item.view.*

class CreateRVAdapter(private val items : ProjectMemberResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(v: View, position: Int)
    }
    private var itemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(itemClickListener: OnItemClickListener){
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.chat_create_user_item, parent, false)
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

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: ProjectMemberResponse.User){
            view.create_user_item_name_tv.text = item.name

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.setOnClickListener{
                    itemClickListener?.onItemClick(itemView, position)
                }
            }
        }
    }
}