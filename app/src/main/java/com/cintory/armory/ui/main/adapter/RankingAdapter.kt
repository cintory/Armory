package com.cintory.armory.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.cintory.armory.R
import com.cintory.armory.app.App
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.RankingBean
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.item_ranking.view.*

/**
 * Created by Cintory on 2018/7/31 10:23
 * Email：Cintory@gmail.com
 */
class RankingAdapter(t: MutableList<RankingBean>) :
    BaseAdapter<RankingBean, RankingAdapter.RankHolder>(t) {
  override fun getCreateViewHolder(parent: ViewGroup, viewType: Int): RankHolder {
    return RankHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_ranking, parent, false)
    )
  }

  override fun getBindViewHolder(holder: RankHolder, position: Int) {
    holder.itemView.tv_average_item_level.text = items[position].itemLevel.toString()
    holder.itemView.tv_username.text = items[position].name
    holder.itemView.tv_total.text = items[position].total.toString()
    val className = App.instance.mCacheManager.mClassList!![items[position].classID - 1]
    GlideApp.with(holder.itemView).load(
        className.specs!![items[position].spec - 1].getSpecsImage(
            className.name!!
        )
    )
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_spec)

    GlideApp.with(holder.itemView).load(items[position].talents[0].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_15)
    GlideApp.with(holder.itemView).load(items[position].talents[1].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_30)
    GlideApp.with(holder.itemView).load(items[position].talents[2].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_45)
    GlideApp.with(holder.itemView).load(items[position].talents[3].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_60)
    GlideApp.with(holder.itemView).load(items[position].talents[4].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_75)
    GlideApp.with(holder.itemView).load(items[position].talents[5].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_90)
    GlideApp.with(holder.itemView).load(items[position].talents[6].getTalentImage())
        .transforms(RoundedCorners(10))
        .into(holder.itemView.iv_talent_100)

    holder.itemView.setOnClickListener {
      clickListener?.onItemClick(
          position,
          holder.itemView,
          holder
      )
    }
  }

  class RankHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
