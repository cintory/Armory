package com.cintory.armory.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cintory.armory.R
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.TableDetailBean
import com.cintory.armory.model.http.api.WarcraftLogsApi
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.item_spell_detail.view.*

/**
 * Created by Cintory on 2018/8/6 21:34
 * Email：Cintory@gmail.com
 */
class SpellCountAdapter(t: MutableList<TableDetailBean>) :
    BaseAdapter<TableDetailBean, SpellCountAdapter.SpellCountHolder>(t) {
    override fun getCreateViewHolder(parent: ViewGroup, viewType: Int): SpellCountHolder {
        return SpellCountHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_spell_detail, parent, false)
        )
    }

    override fun getBindViewHolder(holder: SpellCountHolder, position: Int) {
        GlideApp.with(holder.itemView.context)
            .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].abilityIcon}")
            .centerCrop()
            .into(holder.itemView.iv_spell_icon)
        holder.itemView.tv_spell_name.text = items[position].name
        holder.itemView.tv_cast_count.text = "Casts:\n${items[position].uses}"
        var max = 0
        var min = 0
        items[position].hitdetails.forEach {
            max = if (max > it.max) max else it.max
            min = if (min == 0) it.min else if (min < it.min) min else it.min
        }
        holder.itemView.tv_max_hit.text = "Max:\n$max"
        holder.itemView.tv_min_hit.text = "Min:\n$min"
        holder.itemView.tv_critical_percent.text =
                "Crit %\n${items[position].critHitCount} / ${items[position].hitCount}"
        holder.itemView.tv_total.text = items[position].total.toString()
        if (items[position].uses > 0) {
            val dpc = items[position].total / items[position].uses
            holder.itemView.tv_damage_per_cast.text = "Avg Cast:\n$dpc"
        } else {
            holder.itemView.tv_damage_per_cast.text = "Avg Cast:\n-"
        }
    }


    class SpellCountHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}