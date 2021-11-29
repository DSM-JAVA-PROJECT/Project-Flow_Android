package com.example.project_flow_android.base

import android.graphics.PorterDuff
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.project_flow_android.R

object BaseBindingAdapter {
    @JvmStatic
    @BindingAdapter("recycler_view_adapter")
    fun recyclerViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>?
    ) {
        val setLayoutManager = LinearLayoutManager(recyclerView.context)
        setLayoutManager.orientation = RecyclerView.VERTICAL
        recyclerView.layoutManager = setLayoutManager
        if (adapter != null) {
            recyclerView.adapter = adapter
        }
    }

    @JvmStatic
    @BindingAdapter("glide_image_load")
    fun glideImageLoad(imageView: ImageView, resource: String?) {
        val circularProgressDrawable = CircularProgressDrawable(imageView.context)
        circularProgressDrawable.strokeWidth = 10f
        circularProgressDrawable.centerRadius = 40f
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(imageView.context, R.color.blue_main), PorterDuff.Mode.SRC_IN )
        circularProgressDrawable.start()

        Glide.with(imageView.context)
            .load(resource)
            .placeholder(circularProgressDrawable)
            .error(R.drawable.ic_baseline_account_circle_24)
            .into(imageView)
    }
}