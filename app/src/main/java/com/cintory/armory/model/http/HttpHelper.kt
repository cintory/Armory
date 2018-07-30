package com.cintory.armory.model.http

import com.cintory.armory.model.bean.*
import com.cintory.armory.model.bean.http.RankingResponse
import io.reactivex.Single

/**
 * 作者：Cintory on 2018/7/24 18:18
 * 邮箱：Cintory@gmail.com
 */
interface HttpHelper {

    fun getZone(): Single<List<ZonesBean>>

    fun getClass(): Single<List<ClassBean>>


    fun getRankings(
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
    ): Single<RankingResponse>

    fun getFights(reportID: String): Single<FightsBean>

    fun getTables(
        view: String,
        reportID: String,
        start: String,
        end: String,
        hostility: String,
        by: String,
        sourceID: String,
        sourceInstance: String,
        sourceClass: String,
        targetID: String,
        targetInstance: String,
        targetClass: String,
        abilityID: String,
        options: String,
        cutoff: String,
        encounter: String,
        wipes: String,
        difficulty: String,
        filter: String,
        translate: Boolean
    ): Single<TablesBean>
}