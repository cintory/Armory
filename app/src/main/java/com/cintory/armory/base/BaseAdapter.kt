package com.cintory.armory.base

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

/**
 * 作者：Cintory on 2018/7/25 11:16
 * 邮箱：Cintory@gmail.com
 */
open abstract class BaseAdapter<T, V : RecyclerView.ViewHolder>(t: List<T>) :
    RecyclerView.Adapter<V>() {
    var items: ArrayList<T>

    init {
        items = ArrayList()

    }

    override fun getItemCount() = items.size

    fun addNews(news: List<T>) {
        val initPosition = items.size
        items.addAll(news)
        notifyItemRangeChanged(initPosition, items.size)
    }

    fun clearAndAddNews(news: List<T>) {
        items.clear()
        notifyDataSetChanged()
        items.addAll(news)
        notifyItemRangeInserted(0, items.size)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): V {
        return getCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: V , position: Int) {
        getBindViewHolder(holder, position)
    }

    abstract fun getCreateViewHolder(parent: ViewGroup, viewType: Int): V

    abstract fun getBindViewHolder(holder: V , position: Int)

    var clickListener: OnItemClickListener? = null
    fun setOnClickListener(mClickListener: OnItemClickListener) {
        clickListener = mClickListener
    }


    interface OnItemClickListener {
        fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder)
    }
}