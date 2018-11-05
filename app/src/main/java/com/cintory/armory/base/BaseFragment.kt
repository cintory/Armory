package com.cintory.armory.base

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * 作者：Cintory on 2018/7/23 18:55
 * 邮箱：Cintory@gmail.com
 */
abstract class BaseFragment : Fragment() {

  protected lateinit var mView: View
  protected lateinit var mActivity: AppCompatActivity

  override fun onAttach(context: Context?) {
    super.onAttach(context)
  }

  override fun onCreateView(
      inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
  ): View? {
    mView = inflater.inflate(getLayoutID(), null)
    return mView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    mActivity = view.context as AppCompatActivity
    initEventAndData()
    initInject()
  }

  protected abstract fun getLayoutID(): Int

  protected abstract fun initEventAndData()

  protected abstract fun initInject()

}