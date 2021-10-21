package com.example.project_flow_android.ui.chat

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.data.chat.RoomListResponse
import kotlinx.android.synthetic.main.chat_list_item.view.*

class RoomRVAdapter(private val items : RoomListResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.chat_list_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder)
            holder.apply {
                bind(items.responses[position])
            }
    }

    override fun getItemCount() = items.responses.size

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item : RoomListResponse.ChatRoomResponse){
            if(item.chatRoomImage != null){
                view.chat_list_iv.setImageURI(Uri.parse(item.chatRoomImage))
            }
            view.chat_list_name_tv.text = item.chatRoomName
        }
    }
}