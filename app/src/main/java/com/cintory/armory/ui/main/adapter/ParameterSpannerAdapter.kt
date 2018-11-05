package com.cintory.armory.ui.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.cintory.armory.R
import com.cintory.armory.model.bean.ItemBean
import kotlinx.android.synthetic.main.item_spinner.view.*

/**
 * Created by Cintory on 2018/7/30 10:49
 * Email：Cintory@gmail.com
 */
class ParameterSpannerAdapter(val mContext: Context, var mData: MutableList<ItemBean>) :
    BaseAdapter() {


  override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view = LayoutInflater.from(mContext).inflate(R.layout.item_spinner, null)
    view.tv_spinner.text = mData[position].name
    return view
  }

  override fun getItem(position: Int): Any {
    return mData[position]
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun getCount(): Int {
    return mData.size
  }

}