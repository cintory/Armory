package com.cintory.armory.model.http

import com.cintory.armory.model.bean.*
import com.cintory.armory.model.bean.http.RankingResponse
import com.cintory.armory.model.bean.http.TableResponse
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

    fun getTablesBySourceID(
        view: String,
        reportID: String,
        start: Long,
        end: Long,
        hostility: String? = null,
        by: String? = null,
        sourceID: String,
        sourceInstance: String? = null,
        sourceClass: String? = null,
        targetID: String? = null,
        targetInstance: String? = null,
        targetClass: String? = null,
        abilityID: String? = null,
        options: String? = null,
        cutoff: String? = null,
        encounter: String? = null,
        wipes: String? = null,
        difficulty: String? = null,
        filter: String? = null,
        translate: Boolean? = null
    ): Single<TableResponse<TableDetailBean>>

    fun getTables(
        view: String,
        reportID: String,
        start: Long,
        end: Long,
        hostility: String? = null,
        by: String? = null,
        sourceInstance: String? = null,
        sourceClass: String? = null,
        targetID: String? = null,
        targetInstance: String? = null,
        targetClass: String? = null,
        abilityID: String? = null,
        options: String? = null,
        cutoff: String? = null,
        encounter: String? = null,
        wipes: String? = null,
        difficulty: String? = null,
        filter: String? = null,
        translate: Boolean? = null
    ): Single<TableResponse<TableOverviewBean>>
}