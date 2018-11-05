package com.cintory.armory.base.contract.main

import com.cintory.armory.base.BasePresenter
import com.cintory.armory.base.BaseView
import com.cintory.armory.model.bean.FightsBean
import com.cintory.armory.model.bean.TableOverviewBean

/**
 * Created by Cintory on 2018/8/2 14:19
 * Email：Cintory@gmail.com
 */
interface TableOverviewContract {
  interface View : BaseView {
    fun setTableData(
        data: List<TableOverviewBean>,
        fight: FightsBean.FightsEntity,
        logTime: Long
    )

  }

  interface Presenter : BasePresenter<View> {
    fun getContent(
        view: String,
        reportID: String,
        fightID: Int
    )
  }
}