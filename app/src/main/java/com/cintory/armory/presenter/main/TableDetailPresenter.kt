package com.cintory.armory.presenter.main

import com.cintory.armory.base.RxPresenter
import com.cintory.armory.base.contract.main.TableDetailContract
import com.cintory.armory.model.bean.TableDetailBean
import com.cintory.armory.model.bean.http.TableResponse
import com.cintory.armory.model.http.HttpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Cintory on 2018/8/7 15:48
 * Email：Cintory@gmail.com
 */
class TableDetailPresenter @Inject constructor(private val mHttpHelper: HttpHelper) :
    RxPresenter<TableDetailContract.View>(), TableDetailContract.Presenter {

  override fun getTableData(
      start: Long,
      end: Long,
      sourceID: Int,
      view: String,
      reportID: String?,
      fightID: Int
  ) {
    addSubscribe(
        mHttpHelper.getTablesBySourceID(
            view = view,
            reportID = reportID!!,
            start = start,
            end = end,
            sourceID = sourceID.toString()
        )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe { t: TableResponse<TableDetailBean> ->
              mView?.setContent(t.entries
                  .sortedByDescending { it.total })
            })
  }
}