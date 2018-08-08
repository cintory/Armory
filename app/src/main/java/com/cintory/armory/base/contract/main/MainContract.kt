package com.cintory.armory.base.contract.main

import com.cintory.armory.base.BasePresenter
import com.cintory.armory.base.BaseView
import com.cintory.armory.model.bean.ClassBean
import com.cintory.armory.model.bean.RankingBean
import com.cintory.armory.model.bean.RankingsBean
import com.cintory.armory.model.bean.ZonesBean

/**
 * 作者：Cintory on 2018/7/23 22:36
 * 邮箱：Cintory@gmail.com
 */
interface MainContract {
    interface View : BaseView {
        fun setContent(data: List<RankingBean>)

        fun setEncounter(zoneID: Int, encounterID: Int)

        fun setSpec(classID: Int, specID: Int?)
    }

    interface Presenter : BasePresenter<View> {

        fun getRankData(
            encounterID: Int,
            metric: String?,
            size: String,
            difficulty: String,
            partition: String,
            classX: String,
            spec: String,
            bracket: String,
            limit: String,
            guild: String,
            server: String,
            region: String,
            page: String,
            filter: String
        )


    }
}