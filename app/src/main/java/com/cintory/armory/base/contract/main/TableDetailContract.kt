package com.cintory.armory.base.contract.main

import com.cintory.armory.base.BasePresenter
import com.cintory.armory.base.BaseView
import com.cintory.armory.model.bean.TableDetailBean

/**
 * Created by Cintory on 2018/8/1 16:14
 * Email：Cintory@gmail.com
 */
interface TableDetailContract {
  interface View : BaseView {

    fun setContent(data: List<TableDetailBean>)

  }

  interface Presenter : BasePresenter<View> {

    fun getTableData(
        start: Long,
        end: Long,
        sourceID: Int,
        view: String,
        reportID: String?,
        fightID: Int
    )
  }
}