package com.cintory.armory.model.http.api

import com.cintory.armory.model.bean.*
import com.cintory.armory.model.bean.http.RankingResponse
import com.cintory.armory.model.bean.http.TableResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * 作者：Cintory on 2018/7/24 17:22
 * 邮箱：Cintory@gmail.com
 */
interface WarcraftLogsApi {

    /**
     * Gets an array of Zone objects. Each zone corresponds to a raid/dungeon instance in the game
     * and has its own set of encounters.
     */
    @GET("zones")
    fun getZone(): Single<List<ZonesBean>>

    /**
     * Gets an array of Class objects. Each Class corresponds to a class in the game.
     */
    @GET("classes")
    fun getClasses(): Single<List<ClassBean>>

    /**
     * Gets an object that contains a total count and an array of EncounterRanking objects and a
     * total number of rankings for that encounter. Each EncounterRanking corresponds to a single
     * character or guild/team.
     *
     * @param encounterID The encounter to collect rankings for. Encounter IDs can be obtained
     * using
     * a /zones request.
     * @param metric The metric to query for. Valid fight metrics are 'speed', 'execution' and
     * 'feats'. Valid character metrics are 'dps', 'hps', 'bossdps, 'tankhps', or 'playerspeed'.
     * For
     * WoW only, 'krsi' can be used for tank survivability ranks.
     * @param size The raid size to query for. This is only valid for fixed size raids. Raids with
     * flexible sizing must omit this parameter.
     * @param difficulty The difficulty setting to query for. Valid difficulty settings are 1 =
     * LFR,
     * 2 = Flex, 3 = Normal, 4 = Heroic, 5 = Mythic, 10 = Challenge Mode, 100 = WildStar/FF. Can be
     * omitted for encounters with only one difficulty setting.
     * @param partition The partition group to query for. Most zones have only one partition, and
     * this can be omitted. Hellfire Citadel has two partitions (1 for original, 2 for pre-patch).
     * Highmaul and BRF have two partitions (1 for US/EU, 2 for Asia).
     * @param classX The class to query for if a character metric is specified. Valid class IDs can
     * be obtained from a /classes API request. Optional.
     * @param spec The spec to query for if a character metric is specified. Valid spec IDs can be
     * obtained from a /classes API request. Optional.
     * @param bracket The bracket to query for. If omitted or if a value of 0 is specified, then
     * all
     * brackets are examined. Brackets can be obtained from a /zones API request.
     * @param limit The number of results to return at a time. If omitted, a default of 200 is
     * assumed. Values greater than 5000 are not allowed.
     * @param guild An optional guild to filter on. If set, the server and region must also be
     * specified.
     * @param server A server to filter on. If set, the region must also be specified. This is the
     * slug field in Blizzard terminology.
     * @param region The short name of a region to filter on (e.g., US, NA, EU).
     * @param page The page to examine, starting from 1. If the value is omitted, then 1 is
     * assumed.
     * For example, with a page of 2 and a limit of 300, you will be fetching rankings 301-600.
     * @param filter A search filter string, limiting the search to specific classes, specs, fight
     * durations, raid sizes, etc. The format should match the string used on the public rankings
     * pages.
     */
    @GET("rankings/encounter/{encounterID}")
    fun getRankings(
        @Path("encounterID") encounterID: Int,
        @Query("metric") metric: String?,
        @Query("size") size: String?,
        @Query("difficulty") difficulty: String,
        @Query("partition") partition: String,
        @Query("class") classX: String?,
        @Query("spec") spec: String?,
        @Query("bracket") bracket: String?,
        @Query("limit") limit: String?,
        @Query("guild") guild: String?,
        @Query("server") server: String?,
        @Query("region") region: String?,
        @Query("page") page: String?,
        @Query("filter") filter: String?
    ): Single<RankingResponse>

    /**
     * Gets an array of CharacterRanking objects. Each CharacterRanking corresponds to a single rank
     * on a fight for the specified character.
     *
     * @param characterName The name of the character to collect rankings for.
     * @param serverName The server that the character is found on. For World of Warcraft this is
     * the 'slug' field returned from their realm status API.
     * @param serverRegion The short region name for the server on which the character is located:
     * US, EU, KR, TW, CN.
     * @param zone The zone to fetch rankings for. If omitted, the latest open raid zone is used.
     * @param encounter An encounter within the zone to fetch rankings for. If omitted, all
     * encounters in the zone will be checked.
     * @param metric The metric to query for. Valid character metrics are 'dps', 'hps', 'bossdps,
     * 'tankhps', or 'playerspeed'. For WoW only, 'krsi' can be used for tank survivability ranks.
     * @param bracket The bracket to query for. If omitted or if a value of 0 is specified, then
     * all
     * brackets are examined. Brackets can be obtained from a /zones API request.
     * @param partition The partition group to query for. Most zones have only one partition, and
     * this can be omitted. Hellfire Citadel has two partitions (1 for original, 2 for pre-patch).
     * Highmaul and BRF have two partitions (1 for US/EU, 2 for Asia).
     */

    @GET("rankings/character/{characterName}/{serverName}/{serverRegion}")
    fun getCharacterRankingBean(
        @Path("characterName") characterName: String,
        @Path("serverName") serverName: String,
        @Path("serverRegion") serverRegion: String,
        @Query("zone") zone: String,
        @Query("encounter") encounter: String,
        @Query("metric") metric: String,
        @Query("bracket") bracket: Int, @Query("partition") partition: Int
    ): Single<List<CharacterRankingBean>>

    /**
     * Obtains all parses for a character in the zone across all specs. Every parse is included and
     * not just rankings.
     *
     * @param guildName The name of the guild to collect reports for.
     * @param serverName The server that the guild is found on. For World of Warcraft this is the
     * 'slug' field returned from their realm status API.
     * @param serverRegion The short region name for the server on which the guild is located: US,
     * EU, KR, TW, CN.
     * @param start An optional start time. This is a UNIX timestamp but with millisecond
     * precision.
     * If omitted, 0 is assumed.
     * @param end An optional end time. This is a UNIX timestamp but with millisecond precision. If
     * omitted, the current time is assumed.
     */
    @GET("parses/character/{characterName}/{serverName}/{serverRegion}")
    fun parses(
        @Path("guildName") guildName: String,
        @Path("serverName") serverName: String,
        @Path("serverRegion") serverRegion: String,
        @Query("start") start: Int, @Query("end") end: Int
    ): Single<List<CharacterRankingBean>>

    /**
     * Gets an array of Report objects. Each Report corresponds to a single calendar report for the
     * specified guild.
     *
     * @param guildName The name of the guild to collect reports for.
     * @param serverName The server that the guild is found on. For World of Warcraft this is the
     * 'slug' field returned from their realm status API.
     * @param serverRegion The short region name for the server on which the guild is located: US,
     * EU, KR, TW, CN.
     * @param start An optional start time. This is a UNIX timestamp but with millisecond
     * precision.
     * If omitted, 0 is assumed.
     * @param end An optional end time. This is a UNIX timestamp but with millisecond precision. If
     * omitted, the current time is assumed.
     */
    @GET("reports/guild/{guildName}/{serverName}/{serverRegion}")
    fun getReports(
        @Path("guildName") guildName: String,
        @Path("serverName") serverName: String,
        @Path("serverRegion") serverRegion: String,
        @Query("start") start: Int, @Query("end") end: Int
    ): Single<List<ReportBean>>

    /**
     * Gets an array of Report objects. Each Report corresponds to a single calendar report for the
     * specified user's personal logs.
     *
     * @param userName The name of the user to collect reports for.
     * @param start An optional start time. This is a UNIX timestamp but with millisecond
     * precision.
     * If omitted, 0 is assumed.
     * @param end An optional end time. This is a UNIX timestamp but with millisecond precision. If
     * omitted, the current time is assumed.
     */
    @GET("reports/user/{userName}")
    fun getReports(
        @Path("userName") userName: String, @Query("start") start: Int, @Query("end") end: Int
    ): Single<List<ReportBean>>

    /**
     * Gets arrays of fights and the participants in those fights. Each Fight corresponds to a
     * single pull of a boss.
     *
     * @param code The specific report to collect fights and participants for.
     * @param translate An optional flag indicating that the results should be translated into the
     * language of the host (e.g., cn.warcraftlogs.com would get Chinese results).
     */
    @GET("report/fights/{code}")
    fun getFights(
        @Path("code") code: String,
        @Query("translate") translate: Boolean
    ): Single<FightsBean>

    /**
     * Gets an array of events, such as damage, healing, cast, buff and debuff events. A maximum of
     * 300 events will be returned from this API call. A nextPageTimestamp field will be included in
     * the return result, and you can issue another query with that as the new start to keep
     * fetching events.
     *
     * @param code The specific report to collect events for.
     * @param start A start time. This is a time from the start of the report in milliseconds. If
     * omitted, 0 is assumed.
     * @param end An end time. This is a time from the start of the report in milliseconds. If
     * omitted, 0 is assumed.
     * @param actorid An optional actor ID to filter to. If set, only events where the ID matches
     * the source or target of the event will be returned. The actor's pets will also be included.
     * @param actorinstance An optional actor instance ID to filter to. If set, only events where
     * the instance ID matches the source or target of the event will be returned. This is useful
     * to
     * look for all events involving NPC N, where N is the actor instance ID.
     * @param actorclass An optional actor class to filter to. If set, only events where the source
     * or target involves that class (e.g., Mage) will be returned.
     * @param cutoff An optional death cutoff. If set, events after that number of deaths have
     * occurred will not be examined.
     * @param encounter An optional encounter filter. If set to a specific encounter ID, only
     * fights
     * involving a specific encounter will be considered. The encounter IDs match those used in
     * rankings/statistics.
     * @param wipes An optional wipes filter. If set to 1, only wipes will be considered.
     * @param difficulty An optional difficulty filter.
     * @param filter An optional filter written in WCL's expression language. Events must match the
     * filter to be included.
     * @param translate An optional flag indicating that the results should be translated into the
     * language of the host (e.g., cn.warcraftlogs.com would get Chinese results).
     */

    @GET("report/events/{code}")
    fun getReportEvents(
        @Path("code") code: String,
        @Query("start") start: Int,
        @Query("end") end: Int,
        @Query("actorid") actorid: Int,
        @Query("actorinstance") actorinstance: Int,
        @Query("actorclass") actorclass: String,
        @Query("cutoff") cutoff: Int,
        @Query("encounter") encounter: Int,
        @Query("wipes") wipes: Int,
        @Query("difficulty") difficulty: Int,
        @Query("filter") filter: Int, @Query("translate") translate: Boolean
    ): Single<List<ReportBean>>

    /**
     * Gets a table of entries, either by actor or ability, of damage, healing and cast totals for
     * each entry. This API exactly follows what is returned for the Tables panes on the site. It
     * can and will change as the needs of those panes do, and as such should never be considered a
     * frozen API. Use at your own risk.
     *
     * @param view The type of data requested. Supported values are 'damage-done', 'damage-taken',
     * 'healing', 'casts', 'summons', 'buffs', 'debuffs', 'deaths', 'survivability', 'resources'
     * and
     * 'resources-gains'.
     * @param code The specific report to collect table entries for.
     * @param start A start time. This is a time from the start of the report in milliseconds. If
     * omitted, 0 is assumed.
     * @param end An end time. This is a time from the start of the report in milliseconds. If
     * omitted, 0 is assumed.
     * @param hostility An optional hostility value of 0 or 1. The default is 0. A value of 0 means
     * to collect data for Friendlies. A value of 1 means to collect data for Enemies.
     * @param by An optional parameter indicating how to group entries. They can be grouped by
     * 'source', by 'target', or by 'ability'. This value matches WCL's default behavior if
     * omitted.
     * For buffs and debuffs, a value of 'source' means auras gained by the source, and a value of
     * 'target' means auras cast by the source. This value is not used in the 'deaths',
     * 'survivability', 'resources' and 'resources-gains' views.
     * @param sourceid An optional actor ID to filter to. If set, only events where the ID matches
     * the source (or target for damage-taken) of the event will be returned. The actor's pets will
     * also be included (unless the options field overrides).
     * @param sourceinstance An optional actor instance ID to filter to. If set, only events where
     * the instance ID matches the source (or target for damage-taken) of the event will be
     * returned. This is useful to look for all events involving NPC N, where N is the actor
     * instance ID.
     * @param sourceclass An optional actor class to filter to. If set, only events where the
     * source
     * (or target for damage-taken) involves that class (e.g., Mage) will be returned.
     * @param targetid An optional actor ID to filter to. If set, only events where the ID matches
     * the target (or source for damage-taken) of the event will be returned. This value is not
     * used
     * in the 'deaths', 'survivability', 'resources' and 'resources-gains' views.
     * @param targetinstance An optional actor instance ID to filter to. If set, only events where
     * the instance ID matches the target (or source for damage-taken) of the event will be
     * returned. This is useful to look for all events involving NPC N, where N is the actor
     * instance ID. This value is not used in the 'deaths', 'survivability', 'resources' and
     * 'resources-gains' views.
     * @param targetclass An optional actor class to filter to. If set, only events where the
     * target
     * (or source for damage-taken) involves that class (e.g., Mage) will be returned. This value
     * is
     * not used in the 'deaths', 'survivability', 'resources' and 'resources-gains' views.
     * @param abilityid An optional ability ID to filter to. If set, only events where the ability
     * matches will be returned. Consolidated abilities (WCL only) are represented using a negative
     * number that matches the ability ID that everything is consolidated under. For the 'deaths'
     * view, this represents a specific killing blow. For the resources views, the abilityid is not
     * an ability but a resource type. Valid resource types can be viewed at
     * https://www.warcraftlogs.com/reports/resource_types/
     * @param options A set of options for what to include/exclude. These correspond to options
     * like
     * Include Overkill in the Damage Done pane. Complete list will be forthcoming. If omitted,
     * appropriate defaults that match WCL's default behavior will be chosen. This value is not
     * used
     * in the 'deaths', 'survivability', 'resources' and 'resources-gains' views.
     * @param cutoff An optional death cutoff. If set, events after that number of deaths have
     * occurred will not be examined.
     * @param encounter An optional encounter filter. If set to a specific encounter ID, only
     * fights
     * involving a specific encounter will be considered. The encounter IDs match those used in
     * rankings/statistics.
     * @param wipes An optional wipes filter. If set to 1, only wipes will be considered.
     * @param difficulty An optional difficulty filter.
     * @param filter An optional filter written in WCL's expression language. Events must match the
     * filter to be included.
     * @param translate An optional flag indicating that the results should be translated into the
     * language of the host (e.g., cn.warcraftlogs.com would get Chinese results).
     */
    @GET("report/tables/{view}/{code}")
    fun getReportTablesBySourceID(
        @Path("view") view: String,
        @Path("code") code: String,
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("hostility") hostility: String?,
        @Query("by") by: String?,
        @Query("sourceid") sourceid: String,
        @Query("sourceinstance") sourceinstance: String?,
        @Query("sourceclass") sourceclass: String?,
        @Query("targetid") targetid: String?,
        @Query("targetinstance") targetinstance: String?,
        @Query("targetclass") targetclass: String?,
        @Query("abilityid") abilityid: String?,
        @Query("options") options: String?,
        @Query("cutoff") cutoff: String?,
        @Query("encounter") encounter: String?,
        @Query("wipes") wipes: String?,
        @Query("difficulty") difficulty: String?,
        @Query("filter") filter: String?,
        @Query("translate") translate: Boolean?
    ): Single<TableResponse<TableDetailBean>>

    @GET("report/tables/{view}/{code}")
    fun getReportTables(
        @Path("view") view: String,
        @Path("code") code: String,
        @Query("start") start: Long,
        @Query("end") end: Long,
        @Query("hostility") hostility: String?,
        @Query("by") by: String?,
        @Query("sourceinstance") sourceinstance: String?,
        @Query("sourceclass") sourceclass: String?,
        @Query("targetid") targetid: String?,
        @Query("targetinstance") targetinstance: String?,
        @Query("targetclass") targetclass: String?,
        @Query("abilityid") abilityid: String?,
        @Query("options") options: String?,
        @Query("cutoff") cutoff: String?,
        @Query("encounter") encounter: String?,
        @Query("wipes") wipes: String?,
        @Query("difficulty") difficulty: String?,
        @Query("filter") filter: String?,
        @Query("translate") translate: Boolean?
    ): Single<TableResponse<TableOverviewBean>>

    companion object {

        val HOST = "https://www.warcraftlogs.com:443/v1/"
        val IMAGE_HOST = "https://dmszsuqyoe6y6.cloudfront.net/"
    }
}
