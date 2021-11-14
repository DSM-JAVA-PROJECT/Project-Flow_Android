package com.example.project_flow_android.ui.chat

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import kotlinx.android.synthetic.main.chat_item_mine.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.chat_other_name_tv
import kotlinx.android.synthetic.main.chat_item_other.view.chat_other_profile_iv
import kotlinx.android.synthetic.main.chat_join_plan_item_mine.view.*
import kotlinx.android.synthetic.main.chat_join_plan_item_other.view.*
import kotlinx.android.synthetic.main.chat_plan_item_mine.view.*
import java.lang.RuntimeException
import java.text.ParseException
import java.text.SimpleDateFormat

class ChatRVAdapter(private val items: ChatMessageResponse, private val activity: Activity) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val MINE_TALK = 0
    private val OTHER_TALK = 1
    private val PLAN_ADD = 2
    private val MINE_JOIN_PLAN = 3
    private val OTHER_JOIN_PLAN = 4

    interface OnJoinClickListener{
        fun onJoinClick(v: View, position: Int)
    }
    interface OnShowClickListener{
        fun onShowClick(v: View, position: Int)
    }

    private var joinClickListener: OnJoinClickListener? = null
    private var showClickListener: OnShowClickListener? = null

    fun setOnJoinClickListener(joinClickListener : OnJoinClickListener){
        this.joinClickListener = joinClickListener
    }

    fun setOnShowClickListener(showClickListener: OnShowClickListener){
        this.showClickListener = showClickListener
    }

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
            PLAN_ADD -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_plan_item_mine, parent, false)
                AddPlanViewHolder(inflateView)
            }
            MINE_JOIN_PLAN -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_join_plan_item_mine, parent, false)
                JoinPlanMineViewHolder(inflateView)
            }
            OTHER_JOIN_PLAN -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_join_plan_item_other, parent, false)
                JoinPlanOtherViewHolder(inflateView)
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
        } else if(holder is AddPlanViewHolder){
            holder.apply {
                bind(item)
            }
        } else if(holder is JoinPlanMineViewHolder){
            holder.apply {
                 bind(item)
            }
        } else if(holder is JoinPlanOtherViewHolder){
            holder.apply {
                bind(item)
            }
        }
    }

    override fun getItemCount() = items.oldChatMessageResponses.size

    override fun getItemViewType(position: Int): Int {
        return when(items.oldChatMessageResponses[position].mine){
            true -> {
                when(items.oldChatMessageResponses[position].type){
                    "MESSAGE" -> MINE_TALK
                    "PLAN" -> PLAN_ADD
                    "JOIN_PLAN" -> MINE_JOIN_PLAN
                    else -> throw RuntimeException("알 수 없는 타입")
                }
            }
            false -> {
                when(items.oldChatMessageResponses[position].type){
                    "MESSAGE" -> OTHER_TALK
                    "PLAN" -> PLAN_ADD
                    "JOIN_PLAN" -> OTHER_JOIN_PLAN
                    else -> throw RuntimeException("알 수 없는 타입")
                }
            }
        }
    }

    inner class MineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_mine_content_tv.text = item.message
            dateFormat(item.createdAt, view.chat_mine_time_tv)
        }
    }

    inner class OtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_other_name_tv.text = item.senderName
            view.chat_other_content_tv.text = item.message
            if(item.senderImage != null){
                view.chat_other_profile_iv.clipToOutline = true
                Glide.with(activity).load(Uri.parse(item.senderImage)).into(view.chat_other_profile_iv)
            }
            dateFormat(item.createdAt, view.chat_other_time_tv)
        }
    }

    inner class AddPlanViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.plan_item_mine_content_tv.text = item.planName
            view.plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.plan_item_mine_time_tv)

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.plan_item_mine_join_btn.setOnClickListener{
                    joinClickListener?.onJoinClick(itemView, position)
                }
            }
        }
    }

    inner class JoinPlanMineViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse){
            view.join_plan_item_mine_content_tv.text = item.planName
            view.join_plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.join_plan_item_mine_time_tv)

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.join_plan_item_mine_join_btn.setOnClickListener{
                    joinClickListener?.onJoinClick(itemView, position)
                }
                itemView.join_plan_item_mine_exit_btn.setOnClickListener{
                    showClickListener?.onShowClick(itemView, position)
                }
            }
        }
    }

    inner class JoinPlanOtherViewHolder(v: View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.join_plan_item_other_content_tv.text = item.planName
            view.join_plan_item_other_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.join_plan_item_other_time_tv)
            if(item.senderImage != null){
                view.join_plan_item_other_iv.clipToOutline = true
                Glide.with(activity).load(Uri.parse(item.senderImage)).into(view.join_plan_item_other_iv)
            }

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.join_plan_item_other_btn.setOnClickListener{
                    showClickListener?.onShowClick(itemView, position)
                }
            }
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