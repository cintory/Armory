package com.cintory.armory.model.bean

import com.cintory.armory.model.http.api.WarcraftLogsApi

/**
 * 作者：Cintory on 2018/7/24 18:07
 * 邮箱：Cintory@gmail.com
 */
class ZonesBean {
    /**
     * id : 0
     * name : string
     * frozen : true
     * encounters : [{"id":0,"name":"string","npcID":0}]
     * brackets : [{"id":0,"name":"string"}]
     */

    //The brackets for this zone. RankingsActivity and statistics are collected for each bracket.


    var id: Int = 0//A unique identifier representing this specific zone.
    var name: String? = null//The English name of the raid zone.
    var frozen: Boolean =
        false//Whether or not the rankings and statistics for the zone are frozen.
    // If set, then the results for this zone are never going to change, and you don't have to fetch them again.
    var encounters: List<EncountersEntity>? = null//The encounters for this zone.
    var brackets: BracketEntity? = null
    var partitions: List<PartitionEntity>? = null

    fun getZoneImage() = WarcraftLogsApi.IMAGE_HOST + "img/warcraft/zones/zone-$id-small.jpg"


    class EncountersEntity {
        /**
         * id : 0
         * name : string
         * npcID : 0
         */

        //A unique identifier representing the NPC ID for the zone (WoW Only). Used to feed into WoW's progression API.


        var id: Int = 0//A unique identifier representing this specific encounter.
        var name: String? = null//The English name of the encounter.
        var npcID: Int = 0

        fun getEncounterImage() = WarcraftLogsApi.IMAGE_HOST + "img/warcraft/bosses/$id-icon.jpg"

    }

    class BracketEntity {
        /**
         *  min: 842,
         *  max: 935,
         *  bucket: 3,
         *  type: "Item Level"
         */
        var min: Float = 0f
        var max: Float = 0f
        var bucket: Float = 0f
        var type: String = ""
    }

    class PartitionEntity {
        /**
         *  name: "6.2",
         *  compact: "6.2",
         *  default: true
         */
        var name: String = ""
        var compact: String = ""
        var default: Boolean = false

    }
}
