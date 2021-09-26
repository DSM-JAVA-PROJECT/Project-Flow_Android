package com.example.project_flow_android.ui.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.project_flow_android.R

class ManageRVAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflateView = LayoutInflater.from(parent.context).inflate(R.layout.manage_item, parent, false)
        return ViewHolder(inflateView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder){
            holder.apply {
            }
        }
    }

    override fun getItemCount(): Int {
        return 0
    }

    inner class ViewHolder(v : View) : RecyclerView.ViewHolder(v) {
        val view = v
    }
}