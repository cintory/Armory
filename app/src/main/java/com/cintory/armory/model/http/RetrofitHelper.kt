package com.cintory.armory.model.http

import com.cintory.armory.model.http.api.WarcraftLogsApi
import javax.inject.Inject

/**
 * 作者：Cintory on 2018/7/24 18:21
 * 邮箱：Cintory@gmail.com
 */
class RetrofitHelper @Inject constructor(
    private val mWarcraftLogsService: WarcraftLogsApi
) : HttpHelper {


  override fun getZone() = mWarcraftLogsService.getZone()

  override fun getClass() = mWarcraftLogsService.getClasses()

  override fun getRankings(
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
  ) = mWarcraftLogsService.getRankings(
      encounterID, metric, size, difficulty, partition,
      classX, spec, bracket, limit, guild, server, region, page, filter
  )


  override fun getFights(reportID: String) = mWarcraftLogsService.getFights(
      reportID, true
  )

  override fun getTablesBySourceID(
      view: String,
      reportID: String,
      start: Long,
      end: Long,
      hostility: String?,
      by: String?,
      sourceID: String,
      sourceInstance: String?,
      sourceClass: String?,
      targetID: String?,
      targetInstance: String?,
      targetClass: String?,
      abilityID: String?,
      options: String?,
      cutoff: String?,
      encounter: String?,
      wipes: String?,
      difficulty: String?,
      filter: String?,
      translate: Boolean?
  ) = mWarcraftLogsService.getReportTablesBySourceID(
      view,
      reportID,
      start,
      end,
      hostility,
      by,
      sourceID,
      sourceInstance,
      sourceClass,
      targetID,
      targetInstance,
      targetClass,
      abilityID,
      options,
      cutoff,
      encounter,
      wipes,
      difficulty,
      filter,
      translate
  )

  override fun getTables(
      view: String,
      reportID: String,
      start: Long,
      end: Long,
      hostility: String?,
      by: String?,
      sourceInstance: String?,
      sourceClass: String?,
      targetID: String?,
      targetInstance: String?,
      targetClass: String?,
      abilityID: String?,
      options: String?,
      cutoff: String?,
      encounter: String?,
      wipes: String?,
      difficulty: String?,
      filter: String?,
      translate: Boolean?
  ) = mWarcraftLogsService.getReportTables(
      view,
      reportID,
      start,
      end,
      hostility,
      by,
      sourceInstance,
      sourceClass,
      targetID,
      targetInstance,
      targetClass,
      abilityID,
      options,
      cutoff,
      encounter,
      wipes,
      difficulty,
      filter,
      translate
  )
}