package com.example.project_flow_android.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import kotlinx.android.synthetic.main.chat_item_mine.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.*
import java.lang.RuntimeException
import java.text.ParseException
import java.text.SimpleDateFormat

class ChatRVAdapter(private val items: ChatMessageResponse) :
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
        val item = items.oldChatMessageResponses[position]
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

    override fun getItemCount() = items.oldChatMessageResponses.size

    override fun getItemViewType(position: Int): Int {
        return if (items.oldChatMessageResponses[position].mine) MINE_TALK else OTHER_TALK
    }

    inner class MineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_mine_content_tv.text = item.message
            //view.chat_mine_time_tv.text = item.createdAt
            dateFormat(item.createdAt, view.chat_mine_time_tv)
        }
    }

    inner class OtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_other_content_tv.text = item.message
            //view.chat_other_time_tv.text = item.createdAt
            dateFormat(item.createdAt, view.chat_other_time_tv)
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

    private fun dateFormat(date: String, tv: TextView){
        val old_format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val new_format = SimpleDateFormat("HH:mm")

        try {
            val old_date = old_format.parse(date)
            val new_date = new_format.format(old_date)
            tv.text = new_date
        } catch (e: ParseException){
            e.printStackTrace()
        }
    }
}