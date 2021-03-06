package com.example.project_flow_android.ui.chat

import android.app.Activity
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.ChatMessageResponse
import kotlinx.android.synthetic.main.chat_forced_plan_item_mine.view.*
import kotlinx.android.synthetic.main.chat_item_mine.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.*
import kotlinx.android.synthetic.main.chat_item_other.view.chat_other_name_tv
import kotlinx.android.synthetic.main.chat_item_other.view.chat_other_profile_iv
import kotlinx.android.synthetic.main.chat_join_plan_item_mine.view.*
import kotlinx.android.synthetic.main.chat_join_plan_item_other.view.*
import kotlinx.android.synthetic.main.chat_plan_item_mine.view.*
import kotlinx.android.synthetic.main.chat_resign_plan_item_mine.view.*
import kotlinx.android.synthetic.main.chat_resign_plan_item_mine.view.resign_plan_item_mine_content_tv
import kotlinx.android.synthetic.main.chat_resign_plan_item_other.view.*
import kotlinx.android.synthetic.main.mine_image_item.view.*
import kotlinx.android.synthetic.main.other_image_item.view.*
import kotlinx.android.synthetic.main.room_status_item.view.*
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
    private val MINE_RESIGN_PLAN = 5
    private val OTHER_RESIGN_PLAN = 6
    private val FORCED_PLAN = 7
    private val INVITE = 8
    private val LEAVE = 9
    private val MINE_PICTURE = 10
    private val OTHER_PICTURE = 11

    interface OnJoinClickListener {
        fun onJoinClick(v: View, position: Int)
    }

    interface OnReJoinClickListener {
        fun onReJoinClick(v: View, position: Int)
    }

    interface OnShowClickListener {
        fun onShowClick(v: View, position: Int)
    }

    interface OnResignClickListener {
        fun onResignClick(v: View, position: Int)
    }

    interface OnItemLongClickListener {
        fun onItemLongClick(v: View, position: Int)
    }

    private var joinClickListener: OnJoinClickListener? = null
    private var reJoinClickListener: OnReJoinClickListener? = null
    private var showClickListener: OnShowClickListener? = null
    private var resignClickListener: OnResignClickListener? = null
    private var onItemLongClickListener: OnItemLongClickListener? = null

    fun setOnJoinClickListener(joinClickListener: OnJoinClickListener) {
        this.joinClickListener = joinClickListener
    }

    fun setOnReJoinClickListener(reJoinClickListener: OnReJoinClickListener) {
        this.reJoinClickListener = reJoinClickListener
    }

    fun setOnShowClickListener(showClickListener: OnShowClickListener) {
        this.showClickListener = showClickListener
    }

    fun setOnResignClickListener(resignClickListener: OnResignClickListener) {
        this.resignClickListener = resignClickListener
    }

    fun setOnItemLongClickListener(onItemLongClickListener: OnItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener
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
            MINE_RESIGN_PLAN -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_resign_plan_item_mine, parent, false)
                ResignPlanMineViewHolder(inflateView)
            }
            OTHER_RESIGN_PLAN -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_resign_plan_item_other, parent, false)
                ResignPlanOtherViewHolder(inflateView)
            }
            FORCED_PLAN -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.chat_forced_plan_item_mine, parent, false)
                ForcedPlanViewHolder(inflateView)
            }
            MINE_PICTURE -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.mine_image_item, parent, false)
                MineImageViewHolder(inflateView)
            }
            OTHER_PICTURE -> {
                val inflateView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.other_image_item, parent, false)
                OtherImageViewHolder(inflateView)
            }
            else -> throw RuntimeException("??? ??? ?????? viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items.oldChatMessageResponses[position]
        when (holder) {
            is MineViewHolder -> holder.apply { bind(item) }
            is OtherViewHolder -> holder.apply { bind(item) }
            is AddPlanViewHolder -> holder.apply { bind(item) }
            is JoinPlanMineViewHolder -> holder.apply { bind(item) }
            is JoinPlanOtherViewHolder -> holder.apply { bind(item) }
            is ResignPlanMineViewHolder -> holder.apply { bind(item) }
            is ResignPlanOtherViewHolder -> holder.apply { bind(item) }
            is ForcedPlanViewHolder -> holder.apply { bind(item) }
            is InviteRoomViewHolder -> holder.apply { bind(item) }
            is LeaveRoomViewHolder -> holder.apply { bind(item) }
            is MineImageViewHolder -> holder.apply { bind(item) }
            is OtherImageViewHolder -> holder.apply { bind(item) }
        }
    }

    override fun getItemCount() = items.oldChatMessageResponses.size

    override fun getItemViewType(position: Int): Int {
        val item = items.oldChatMessageResponses[position]
        return when (item.mine) {
            true -> {
                when (item.type) {
                    "MESSAGE" -> MINE_TALK
                    "PLAN" -> PLAN_ADD
                    "JOIN_PLAN" -> MINE_JOIN_PLAN
                    "RESIGN_PLAN" -> MINE_RESIGN_PLAN
                    "FORCED_PLAN" -> FORCED_PLAN
                    "CHATROOM" -> {
                        if (item.profileImage == null) {
                            INVITE
                        } else
                            LEAVE
                    }
                    "PICTURE" -> MINE_PICTURE
                    else -> throw RuntimeException("??? ??? ?????? ??????")
                }
            }
            false -> {
                when (item.type) {
                    "MESSAGE" -> OTHER_TALK
                    "PLAN" -> PLAN_ADD
                    "JOIN_PLAN" -> OTHER_JOIN_PLAN
                    "RESIGN_PLAN" -> OTHER_RESIGN_PLAN
                    "PICTURE" -> OTHER_PICTURE
                    else -> throw RuntimeException("??? ??? ?????? ??????")
                }
            }
        }
    }

    inner class MineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_mine_content_tv.text = item.message
            dateFormat(item.createdAt, view.chat_mine_time_tv)

            itemView.setOnLongClickListener(View.OnLongClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    onItemLongClickListener?.onItemLongClick(itemView, position)
                }
                return@OnLongClickListener true
            })

        }
    }

    inner class OtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.chat_other_name_tv.text = item.senderName
            view.chat_other_content_tv.text = item.message
            imageLoad(view.chat_other_profile_iv, item.senderImage)
            dateFormat(item.createdAt, view.chat_other_time_tv)

            itemView.setOnLongClickListener(View.OnLongClickListener {
                val position = absoluteAdapterPosition
                if (position != RecyclerView.NO_POSITION){
                    onItemLongClickListener?.onItemLongClick(itemView, position)
                }
                return@OnLongClickListener true
            })
        }
    }

    inner class AddPlanViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.plan_item_mine_content_tv.text = item.planName
            view.plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.plan_item_mine_time_tv)

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.plan_item_mine_join_btn.setOnClickListener {
                    joinClickListener?.onJoinClick(itemView, position)
                }
            }
        }
    }

    inner class JoinPlanMineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.join_plan_item_mine_content_tv.text = item.planName
            view.join_plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.join_plan_item_mine_time_tv)

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.join_plan_item_mine_view_btn.setOnClickListener {
                    showClickListener?.onShowClick(itemView, position)
                }
                itemView.join_plan_item_mine_exit_btn.setOnClickListener {
                    resignClickListener?.onResignClick(itemView, position)
                }
            }
        }
    }

    inner class JoinPlanOtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.join_plan_item_other_content_tv.text = item.planName
            view.join_plan_item_other_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.join_plan_item_other_time_tv)
            imageLoad(view.join_chat_other_profile_iv, item.senderImage)

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.join_plan_item_other_btn.setOnClickListener {
                    showClickListener?.onShowClick(itemView, position)
                }
            }
        }
    }

    inner class ResignPlanMineViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.resign_plan_item_mine_content_tv.text = item.planName
            view.resign_plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.resign_plan_item_mine_time_tv)

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.resign_plan_item_mine_join_btn.setOnClickListener {
                    reJoinClickListener?.onReJoinClick(itemView, position)
                }
            }
        }
    }

    inner class ResignPlanOtherViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.resign_plan_item_other_content_tv.text = item.planName
            view.resign_plan_item_other_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.resign_plan_item_other_time_tv)
            imageLoad(view.resign_chat_other_profile_iv, item.senderImage)

            val position = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                itemView.resign_plan_item_other_btn.setOnClickListener {
                    showClickListener?.onShowClick(itemView, position)
                }
            }
        }
    }

    inner class ForcedPlanViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.forced_plan_item_mine_content_tv.text = item.planName
            view.forced_plan_item_mine_date_tv.text = "${item.startDate} ~ ${item.endDate}"
            dateFormat(item.createdAt, view.forced_plan_item_mine_time_tv)
        }
    }

    inner class InviteRoomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.room_status_tv.text = "${item.userName}?????? ???????????? ??????????????????."
        }
    }

    inner class LeaveRoomViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.room_status_tv.text = "${item.userName}?????? ??????????????????."
        }
    }

    inner class MineImageViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            imageLoad(view.mine_image_iv, item.message)
            dateFormat(item.createdAt, view.mine_image_tv)
        }
    }

    inner class OtherImageViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view = v
        fun bind(item: ChatMessageResponse.ChatReceiveResponse) {
            view.other_image_name_tv.text = item.senderName
            imageLoad(view.other_image_profile_iv, item.senderImage)
            imageLoad(view.other_image_iv, item.message)
            dateFormat(item.createdAt, view.other_image_time_tv)
        }
    }

    private fun dateFormat(date: String, tv: TextView) {
        val old_format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS")
        val new_format = SimpleDateFormat("HH:mm")
        try {
            val old_date = old_format.parse(date)
            val new_date = new_format.format(old_date)
            tv.text = new_date
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

    private fun imageLoad(view: ImageView, image: String?) {
        if (image != null) {
            view.clipToOutline = true
            Glide.with(activity).load(Uri.parse(image))
                .into(view)
        }
    }
}