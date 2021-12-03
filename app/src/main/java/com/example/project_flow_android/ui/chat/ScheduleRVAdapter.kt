package com.example.project_flow_android.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R
import com.example.project_flow_android.data.model.sign.chat.DatePlanResponse
import kotlinx.android.synthetic.main.schedule_item.view.*

class ScheduleRVAdapter(private val items: DatePlanResponse) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    interface OnItemClickListener{
        fun onItemClick(v: View, position: Int)
    }

    private var onItemClickListener : OnItemClickListener? = null

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.schedule_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.apply {
                bind(items.planDetails[position])
            }
        }
    }

    override fun getItemCount(): Int = items.planDetails.size

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v){
        val view = v
        fun bind(item: DatePlanResponse.DateResponse){
            view.schedule_plan_tv.text = item.name
            if(item.isFinish){
                view.schedule_iv.setImageResource(R.drawable.ic_baseline_circle_24_finish)
            }

            val position = absoluteAdapterPosition
            if(position != RecyclerView.NO_POSITION){
                itemView.setOnClickListener{
                    onItemClickListener?.onItemClick(itemView, position)
                }
            }
        }
    }
}