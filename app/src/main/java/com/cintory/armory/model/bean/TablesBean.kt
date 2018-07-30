package com.cintory.armory.model.bean

/**
 * 作者：Cintory on 2018/7/24 18:07
 * 邮箱：Cintory@gmail.com
 */
class TablesBean {

    /**
     * entries : []
     * totalTime : 252895
     */

    var totalTime: Int = 0
    var entries: List<EntriesEntity>? = null


    class EntriesEntity {
        /**
         * name : Earth Spirit
         * guid : 138121
         * type : 9
         * total : 62159490
         * totalReduced : 0
         * composite : true
         * abilityIcon : inv_elemental_primal_earth.jpg
         * subentries : []
         * uses : 37
         * hitCount : 177
         * tickCount : 0
         * tickMissCount : 0
         * missCount : 7
         * multistrikeHitCount : 0
         * multistrikeTickCount : 0
         * multistrikeMissCount : 0
         * multistrikeTickMissCount : 0
         * critHitCount : 69
         * critTickCount : 0
         * sources : [{"name":"Earth Spirit","total":62159490,"totalReduced":0,"type":"Pet"}]
         * targets : [{"name":"Portal Keeper Hasabel","total":31767979,"totalReduced":0,"type":"Boss"},{"name":"Blazing
         * Imp","total":26432311,"totalReduced":0,"type":"NPC"},{"name":"Feltouched
         * Skitterer","total":3959200,"totalReduced":0,"type":"NPC"}]
         * hitdetails : [{"type":"Hit","total":29133192,"totalReduced":0,"count":108,"countReduced":0,"absorbOrOverheal":0,"min":9302,"max":1128783},{"type":"Critical
         * Hit","total":33026298,"totalReduced":0,"count":69,"countReduced":0,"absorbOrOverheal":0,"min":19867,"max":2419178}]
         * multistrikedetails : []
         * missdetails : [{"type":"Miss","count":6,"countReduced":0},{"type":"Parry","count":1,"countReduced":0}]
         * multistrikemissdetails : []
         * actor : 21
         * actorName : Worldend
         * actorIcon : Monk
         * actorType : Monk
         * uptime : 11015
         */

        var name: String? = null
        var guid: Int = 0
        var type: Int = 0
        var total: Int = 0
        var totalReduced: Int = 0
        var isComposite: Boolean = false
        var abilityIcon: String? = null
        var uses: Int = 0
        var hitCount: Int = 0
        var tickCount: Int = 0
        var tickMissCount: Int = 0
        var missCount: Int = 0
        var multistrikeHitCount: Int = 0
        var multistrikeTickCount: Int = 0
        var multistrikeMissCount: Int = 0
        var multistrikeTickMissCount: Int = 0
        var critHitCount: Int = 0
        var critTickCount: Int = 0
        var actor: Int = 0
        var actorName: String? = null
        var actorIcon: String? = null
        var actorType: String? = null
        var uptime: Int = 0
        var subentries: List<SubentriesEntity>? = null
        var sources: List<SourcesEntity>? = null
        var targets: List<TargetsEntity>? = null
        var hitdetails: List<HitdetailsEntity>? = null
        var multistrikedetails: List<*>? = null
        var missdetails: List<MissdetailsEntity>? = null
        var multistrikemissdetails: List<*>? = null


        class SubentriesEntity {
            /**
             * name : Spinning Crane Kick
             * guid : 107270
             * actor : 35
             * type : 1
             * actorName : Earth Spirit
             * actorIcon : 138121
             * actorType : Pet
             * total : 22353485
             * totalReduced : 0
             * abilityIcon : ability_monk_cranekick_new.jpg
             * hitCount : 76
             * tickCount : 0
             * tickMissCount : 0
             * missCount : 0
             * multistrikeHitCount : 0
             * multistrikeTickCount : 0
             * multistrikeMissCount : 0
             * multistrikeTickMissCount : 0
             * critHitCount : 27
             * critTickCount : 0
             * sources : [{"name":"Earth Spirit","total":22353485,"totalReduced":0,"type":"Pet"}]
             * targets : [{"name":"Blazing Imp","total":16812226,"totalReduced":0,"type":"NPC"},{"name":"Feltouched
             * Skitterer","total":3910759,"totalReduced":0,"type":"NPC"},{"name":"Portal Keeper
             * Hasabel","total":1630500,"totalReduced":0,"type":"Boss"}]
             * hitdetails : [{"type":"Hit","total":11185384,"totalReduced":0,"count":49,"countReduced":0,"absorbOrOverheal":0,"min":115776,"max":307395},{"type":"Critical
             * Hit","total":11168101,"totalReduced":0,"count":27,"countReduced":0,"absorbOrOverheal":0,"min":231553,"max":627085}]
             * multistrikedetails : []
             * missdetails : []
             * multistrikemissdetails : []
             * uses : 37
             */

            var name: String? = null
            var guid: Int = 0
            var actor: Int = 0
            var type: Int = 0
            var actorName: String? = null
            var actorIcon: String? = null
            var actorType: String? = null
            var total: Int = 0
            var totalReduced: Int = 0
            var abilityIcon: String? = null
            var hitCount: Int = 0
            var tickCount: Int = 0
            var tickMissCount: Int = 0
            var missCount: Int = 0
            var multistrikeHitCount: Int = 0
            var multistrikeTickCount: Int = 0
            var multistrikeMissCount: Int = 0
            var multistrikeTickMissCount: Int = 0
            var critHitCount: Int = 0
            var critTickCount: Int = 0
            var uses: Int = 0
            var sources: List<SubEntitySourcesEntity>? = null
            var targets: List<SubEntityTargetsEntity>? = null
            var hitdetails: List<SubEntityHitdetailsEntity>? = null
            var multistrikedetails: List<*>? = null
            var missdetails: List<*>? = null
            var multistrikemissdetails: List<*>? = null


            class SubEntitySourcesEntity {
                /**
                 * name : Earth Spirit
                 * total : 22353485
                 * totalReduced : 0
                 * type : Pet
                 */

                var name: String? = null
                var total: Int = 0
                var totalReduced: Int = 0
                var type: String? = null
            }

            class SubEntityTargetsEntity {
                /**
                 * name : Blazing Imp
                 * total : 16812226
                 * totalReduced : 0
                 * type : NPC
                 */

                var name: String? = null
                var total: Int = 0
                var totalReduced: Int = 0
                var type: String? = null
            }

            class SubEntityHitdetailsEntity {
                /**
                 * type : Hit
                 * total : 11185384
                 * totalReduced : 0
                 * count : 49
                 * countReduced : 0
                 * absorbOrOverheal : 0
                 * min : 115776
                 * max : 307395
                 */

                var type: String? = null
                var total: Int = 0
                var totalReduced: Int = 0
                var count: Int = 0
                var countReduced: Int = 0
                var absorbOrOverheal: Int = 0
                var min: Int = 0
                var max: Int = 0
            }
        }

        class SourcesEntity {
            /**
             * name : Earth Spirit
             * total : 62159490
             * totalReduced : 0
             * type : Pet
             */

            var name: String? = null
            var total: Int = 0
            var totalReduced: Int = 0
            var type: String? = null
        }

        class TargetsEntity {
            /**
             * name : Portal Keeper Hasabel
             * total : 31767979
             * totalReduced : 0
             * type : Boss
             */

            var name: String? = null
            var total: Int = 0
            var totalReduced: Int = 0
            var type: String? = null
        }

        class HitdetailsEntity {
            /**
             * type : Hit
             * total : 29133192
             * totalReduced : 0
             * count : 108
             * countReduced : 0
             * absorbOrOverheal : 0
             * min : 9302
             * max : 1128783
             */

            var type: String? = null
            var total: Int = 0
            var totalReduced: Int = 0
            var count: Int = 0
            var countReduced: Int = 0
            var absorbOrOverheal: Int = 0
            var min: Int = 0
            var max: Int = 0
        }

        class MissdetailsEntity {
            /**
             * type : Miss
             * count : 6
             * countReduced : 0
             */

            var type: String? = null
            var count: Int = 0
            var countReduced: Int = 0
        }
    }
}
