package com.cintory.armory.ui.main.adapter

import android.content.res.ColorStateList
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cintory.armory.R
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.TableOverviewBean
import com.cintory.armory.model.http.api.WarcraftLogsApi
import com.cintory.armory.util.StringUtil
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.item_table_overview.view.*

/**
 * Created by Cintory on 2018/8/2 17:49
 * Email：Cintory@gmail.com
 */
class TableOverviewAdapter(t: MutableList<TableOverviewBean>) :
    BaseAdapter<TableOverviewBean, TableOverviewAdapter.TableOverviewHolder>(t) {

  var totalDamage: Long = -1
  var maxTotal: Long = -1
  var totalTime: Long = -1

  init {
    totalDamage = 0
    maxTotal = -1
    items.forEach {
      if (maxTotal < it.total)
        maxTotal = it.total
      totalDamage += it.total
    }
  }

  fun addNews(news: List<TableOverviewBean>, totalTime: Long) {
    this.totalTime = totalTime
    news.forEach {
      if (maxTotal < it.total)
        maxTotal = it.total
      totalDamage += it.total
    }
    super.addNews(news)
  }

  fun clearAndAddNews(news: List<TableOverviewBean>, totalTime: Long) {
    this.totalTime = totalTime
    totalDamage = 0
    maxTotal = -1
    news.forEach {
      if (maxTotal < it.total)
        maxTotal = it.total
      totalDamage += it.total
    }
    super.clearAndAddNews(news)
  }

  override fun getBindViewHolder(holder: TableOverviewHolder, position: Int) {
    holder.itemView.tv_name.text = items[position].name
    val url = if (items[position].icon.contains("/")) {
      items[position].icon
    } else {
      "icons/" + items[position].icon
    }
    GlideApp.with(holder.itemView).load(
        WarcraftLogsApi.IMAGE_HOST + "img/warcraft/$url.jpg"
    )
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_spec)
    holder.itemView.tv_total.text = items[position].total.toString()
    holder.itemView.tv_total.text = StringUtil.formatNumber(items[position].total)


    val percent: Double = items[position].total.toDouble() * 100 / maxTotal.toDouble()
    holder.itemView.tv_dps.text = (items[position].total * 1000L / totalTime).toString()
    holder.itemView.pb_percent.progress = percent.toInt()
    val id = holder.itemView.context.resources.getIdentifier(
        items[position].type.toLowerCase(),
        "color",
        holder.itemView.context.packageName
    )
    try {
      holder.itemView.pb_percent.progressTintList =
          ColorStateList.valueOf(holder.itemView.resources.getColor(id))
    } catch (e: Resources.NotFoundException) {
      holder.itemView.pb_percent.progressTintList =
          ColorStateList.valueOf(holder.itemView.resources.getColor(R.color.others))
    }
    if (items[position].talents != null && items[position].talents.size >= 7) {
      holder.itemView.g_talent.visibility = View.VISIBLE
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[0].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_15)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[1].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_30)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[2].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_45)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[3].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_60)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[4].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_75)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[5].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_90)
      GlideApp.with(holder.itemView)
          .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${items[position].talents[6].abilityIcon}")
          .transforms(RoundedCorners(10))
          .into(holder.itemView.iv_talent_100)
    } else {
      holder.itemView.g_talent.visibility = View.GONE
    }

    holder.itemView.setOnClickListener {
      clickListener?.onItemClick(
          position,
          holder.itemView,
          holder
      )
    }
  }

  override fun getCreateViewHolder(parent: ViewGroup, viewType: Int): TableOverviewHolder {
    return TableOverviewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_table_overview, parent, false)
    )
  }

  class TableOverviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}