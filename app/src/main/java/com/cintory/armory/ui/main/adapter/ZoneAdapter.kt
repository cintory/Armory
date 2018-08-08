package com.cintory.armory.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cintory.armory.R
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.ZonesBean
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.item_encounter.view.*
import kotlinx.android.synthetic.main.item_zone.view.*

/**
 * 作者：Cintory on 2018/7/25 11:13
 * 邮箱：Cintory@gmail.com
 */
class ZoneAdapter(t: MutableList<ZonesBean>) : BaseAdapter<ZonesBean, ZoneAdapter.ZoneHolder>(t) {

    lateinit var mEncounterClickListener: OnEncounterClickListener

    override fun getCreateViewHolder(parent: ViewGroup, viewType: Int): ZoneAdapter.ZoneHolder {
        return ZoneHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_zone, parent, false)
        )
    }

    override fun getBindViewHolder(holder: ZoneAdapter.ZoneHolder, position: Int) {
        GlideApp.with(holder.itemView).load(items[position].getZoneImage()).centerCrop()
            .into(holder.itemView.iv_zone_image)
        holder.itemView.tv_zone_name.text = items[position].name
        items[position].encounters?.forEach {
            val encounterView = LayoutInflater.from(holder.itemView.context).inflate(
                R.layout.item_encounter, null, false
            )
            GlideApp.with(encounterView).load(it.getEncounterImage()).centerCrop()
                .into(encounterView.iv_encounter)
            encounterView.tv_encounter_name.text = it.name
            val item = it
            encounterView.setOnClickListener {
                mEncounterClickListener?.onItemClick(
                    items[position].id,
                    item.id
                )
            }
            holder.itemView.ll_encounter.addView(encounterView)
        }
    }

    interface OnEncounterClickListener {
        fun onItemClick(zoneID: Int, encounterID: Int)
    }

    class ZoneHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}