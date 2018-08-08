package com.cintory.armory.presenter.main

import com.cintory.armory.base.RxPresenter
import com.cintory.armory.base.contract.main.TableOverviewContract
import com.cintory.armory.model.bean.FightsBean
import com.cintory.armory.model.bean.TableOverviewBean
import com.cintory.armory.model.bean.http.TableResponse
import com.cintory.armory.model.http.HttpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Cintory on 2018/8/2 14:19
 * Email：Cintory@gmail.com
 */
class TableOverviewPresenter @Inject constructor(private val mHttpHelper: HttpHelper) :
    RxPresenter<TableOverviewContract.View>(), TableOverviewContract.Presenter {
    override fun getContent(view: String, reportID: String, fightID: Int) {
        var fight: FightsBean.FightsEntity? = null
        addSubscribe(mHttpHelper.getFights(reportID)
            .flatMap {
                it.fights!!.forEach { if (it.id == fightID) fight = it }
                mHttpHelper.getTables(
                    view = view,
                    reportID = reportID,
                    start = fight!!.start_time,
                    end = fight!!.end_time
                )
            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { t: TableResponse<TableOverviewBean> ->
                mView?.setTableData(
                    t.entries
                        .sortedByDescending { it.total }, fight!!, t.totalTime
                )
            })
    }

}