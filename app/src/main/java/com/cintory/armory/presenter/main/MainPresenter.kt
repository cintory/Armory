package com.cintory.armory.presenter.main

import com.cintory.armory.base.RxPresenter
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.model.http.HttpHelper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * 作者：Cintory on 2018/7/23 22:45
 * 邮箱：Cintory@gmail.com
 */
class MainPresenter @Inject constructor(private val mHttpHelper: HttpHelper) :
    RxPresenter<MainContract.View>(), MainContract.Presenter {
  override fun getRankData(
      encounterID: Int,
      metric: String?,
      size: String,
      difficulty: String,
      partition: String,
      classID: String,
      spec: String,
      bracket: String,
      limit: String,
      guild: String,
      server: String,
      region: String,
      page: String,
      filter: String
  ) {
    addSubscribe(
        mHttpHelper.getRankings(
            encounterID,
            metric,
            size,
            difficulty,
            partition,
            classID,
            spec,
            bracket,
            limit,
            guild,
            server,
            region,
            page,
            filter
        ).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).subscribe(
            { t ->
              mView?.setContent(t.rankings!!)
            },
            {
              it.printStackTrace()
            })
    )

  }

//    override fun getZoneData() {
//        addSubscribe(mHttpHelper.getZone()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe({ data: List<ZonesBean> -> mView?.setContent(data.filter { !it.frozen }) })
//            { throwable -> throwable.printStackTrace() })
//    }

}

