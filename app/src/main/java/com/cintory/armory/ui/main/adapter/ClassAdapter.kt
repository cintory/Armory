package com.cintory.armory.ui.main.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cintory.armory.R
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.ClassBean
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.item_class.view.*
import kotlinx.android.synthetic.main.item_spec.view.*

/**
 * 作者：Cintory on 2018/7/26 16:29
 * 邮箱：Cintory@gmail.com
 */
class ClassAdapter(t: List<ClassBean>) : BaseAdapter<ClassBean, ClassAdapter.ClassHolder>(t) {

    var mOnClassSelected: OnClassSelected? = null

    override fun getCreateViewHolder(parent: ViewGroup, viewType: Int): ClassHolder {
        return ClassHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_class, parent, false)
        )
    }

    override fun getBindViewHolder(holder: ClassHolder, position: Int) {
        holder.itemView.tv_class_name.text = items[position].name
        holder.itemView.ll_specs.removeAllViews()
        GlideApp.with(holder.itemView).load(items[position].getClassImage()).centerCrop()
            .into(holder.itemView.iv_class_image)
        holder.itemView.setOnClickListener {
            val classBean = items[position]
            classBean.specs = null
            mOnClassSelected?.onItemSelect(classBean)
        }
        items[position].specs?.forEach {
            val specView = LayoutInflater.from(holder.itemView.context).inflate(
                R.layout.item_spec, null, false
            )
            GlideApp.with(specView).load(it.getSpecsImage(items[position].name!!)).centerCrop()
                .into(specView.iv_spec)
            specView.tv_spec_name.text = it.name
            val spec = it
            specView.setOnClickListener {
                val classBean = items[position]
                classBean.specs = listOf(spec)
                mOnClassSelected?.onItemSelect(classBean)
            }
            holder.itemView.ll_specs.addView(specView)
        }
    }

    interface OnClassSelected {
        fun onItemSelect(classBean: ClassBean)
    }

    class ClassHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}