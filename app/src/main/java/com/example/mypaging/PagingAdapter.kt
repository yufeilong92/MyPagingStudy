package com.example.mypaging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class PagingAdapter(val mContext: Context) :
    PagingDataAdapter<DemoBean, PagingAdapter.VH>(diffUtil) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<DemoBean>() {
            override fun areItemsTheSame(oldItem: DemoBean, newItem: DemoBean): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: DemoBean, newItem: DemoBean): Boolean {
                TODO("Not yet implemented")
            }

        }
    }

    class VH(view: View) : RecyclerView.ViewHolder(view) {
        val tv_title = view.findViewById<TextView>(R.id.tv_item_paging)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val bean = getItem(position)
        holder.tv_title.text = "${bean?.name}"
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_paging, parent, false)
        return VH(view)
    }
}