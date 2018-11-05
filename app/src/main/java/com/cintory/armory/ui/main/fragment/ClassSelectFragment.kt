package com.cintory.armory.ui.main.fragment

import androidx.recyclerview.widget.LinearLayoutManager
import com.cintory.armory.R
import com.cintory.armory.app.App
import com.cintory.armory.base.BaseDialogFragment
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.di.component.DaggerFragmentComponent
import com.cintory.armory.di.module.FragmentModule
import com.cintory.armory.model.bean.ClassBean
import com.cintory.armory.model.http.HttpHelper
import com.cintory.armory.ui.main.adapter.ClassAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_select.*
import javax.inject.Inject

/**
 * 作者：Cintory on 2018/7/26 15:43
 * 邮箱：Cintory@gmail.com
 */
class ClassSelectFragment : BaseDialogFragment() {

  @Inject
  lateinit var mHttpHelper: HttpHelper
  lateinit var mClasses: MutableList<ClassBean>
  lateinit var mClassAdapter: ClassAdapter

  override fun getLayoutID() = R.layout.fragment_select

  override fun initInject() {
    DaggerFragmentComponent.builder()
        .appComponent(App.appComponent)
        .fragmentModule(FragmentModule(this))
        .build()
        .inject(this)
  }

  override fun initEventAndData() {
    mClasses = mutableListOf()
    mClassAdapter = ClassAdapter(mClasses)
    mClassAdapter.mOnClassSelected = object : ClassAdapter.OnClassSelected {
      override fun onItemSelect(classID: Int, specID: Int?) {
        if (activity is MainContract.View) {
          (activity as MainContract.View).setSpec(classID, specID)
          dismiss()
        }
      }
    }
    rv_data.layoutManager = LinearLayoutManager(context)
    rv_data.adapter = mClassAdapter
    getEncounterList()

  }

  private fun getEncounterList() {
    addSubscribe(
        mHttpHelper.getClass()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io()).subscribe { data: List<ClassBean> ->
              App.instance.mCacheManager.mClassList = data
              mClassAdapter.addNews(data)
            }
    )
  }

  companion object {
    fun newInstance(): ClassSelectFragment {
      val fragment = ClassSelectFragment()
      return fragment
    }
  }

}