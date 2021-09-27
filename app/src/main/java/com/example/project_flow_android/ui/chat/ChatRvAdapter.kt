package com.example.project_flow_android.ui.chat

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ChatListResponse
import kotlinx.android.synthetic.main.chat_item_mine.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.*
import java.lang.RuntimeException

class ChatRvAdapter(private val items: ChatListResponse) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val MINE_TALK = 0
    private val OTHER_TALK = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MINE_TALK -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_item_mine, parent, false)
                MineViewHolder(inflateView)
            }
            OTHER_TALK -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_item_other, parent, false)
                OtherViewHolder(inflateView)
            }
            else -> throw RuntimeException("알 수 없는 viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items.chatListResponse[position]
        if (holder is MineViewHolder) {
            holder.apply {
                bind(item)
            }
        } else if (holder is OtherViewHolder) {
            holder.apply {
                bind(item)
            }
        }
    }

    override fun getItemCount() = items.chatListResponse.size

    override fun getItemViewType(position: Int): Int {
        return if (items.chatListResponse[position].isMine) MINE_TALK else OTHER_TALK
    }

    inner class MineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatListResponse.ChatContentResponse) {
            view.chat_mine_content_tv.text = item.message
            view.chat_mine_time_tv.text = item.createdAt
        }
    }

    inner class OtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatListResponse.ChatContentResponse) {
            view.chat_other_content_tv.text = item.message
            view.chat_other_time_tv.text = item.createdAt
        }
    }

    inner class MineImage(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind() {

        }
    }

    inner class OtherImage(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind() {

        }
    }
}