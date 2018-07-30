package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

/**
 * 作者：Cintory on 2018/7/24 17:42
 * 邮箱：Cintory@gmail.com
 */
class RankingsBean {
    /**
     * total : 0
     * rankings : [{"name":"string","total":0,"class":0,"spec":0,"guild":"string","server":"string","region":"string","duration":0,"startTime":0,"damageTaken":0,"deaths":0,"itemLevel":0,"patch":0,"reportID":"string","fightID":0,"team":[{"name":"string","class":0,"spec":0}],"size":0}]
     */

    var total: Int = 0//The total number of rankings given the specified filters.
    var rankings: List<RankingsEntity>? = null//An array of EncounterRanking objects.


    class RankingsEntity : Parcelable {
        /**
         * name : string
         * total : 0
         * class : 0
         * spec : 0
         * guild : string
         * server : string
         * region : string
         * duration : 0
         * startTime : 0
         * damageTaken : 0
         * deaths : 0
         * itemLevel : 0
         * patch : 0
         * reportID : string
         * fightID : 0
         * team : [{"name":"string","class":0,"spec":0}]
         * size : 0
         */

        var name: String? = null//The name of the character that obtained the ranking.
        var total: Int = 0//For individual rankings, the DPS/HPS value.
        @SerializedName("class")
        var classX: Int = 0
        // For individual rankings, the class of the character.
        var spec: Int = 0//For individual rankings, the spec of the character.
        var guild: String? = null//The name of the guild that obtained the ranking.
        var server: String? = null//The server that the ranking was obtained on.
        var region: String? = null//The short name of the region that the server belongs to.
        var duration: Int = 0//The length of the fight/run in milliseconds.
        var startTime: Long = 0
        //A timestamp with millisecond precision that indicates when the fight/run happened.
        var damageTaken: Int = 0
        //For fight rankings, how much damage was taken over the course of the fight.
        var deaths: Int = 0// For fight rankings, how much people died on the fight.
        var itemLevel: Int = 0
        // For fight rankings, the average item level of the raid. For character rankings, the item level of the character.
        var patch: Int = 0//For challenge mode rankings, what patch they were obtained in.
        var reportID: String? = null//The report ID that contains this ranking.
        var fightID: Int = 0
        //The fight ID within the report for the fight that contains this ranking.
        var size: Int = 0
        //The size of the raid. Only set for flexible size raids. Remember that flexible raid sizes are all ranked together currently.
        var team: List<TeamEntity>? = null
        //For challenge mode rankings, the team members that made up the team.

        /**
         * startTime : 1513215691396
         * hidden : 0
         * exploit : 0
         * talents : [{"name":"Chi Wave","id":115098},{"name":"Chi Torpedo","id":115008},{"name":"Energizing
         * Elixir","id":115288},{"name":"Ring of Peace","id":116844},{"name":"Dampen
         * Harm","id":122278},{"name":"Hit Combo","id":196740},{"name":"Whirling Dragon
         * Punch","id":152175}]
         * gear : [{"name":"The Wind Blows","quality":"legendary","id":151811},{"name":"Collar of
         * Null-Flame","quality":"epic","id":151973},{"name":"Meditation Spheres of
         * Chi\\'Ji","quality":"epic","id":152147},{"name":"Empty Slot","quality":"common","id":0},{"name":"The
         * Emperor\\'s Capacitor","quality":"legendary","id":144239},{"name":"Dreadhide
         * Girdle","quality":"uncommon","id":121299},{"name":"Leggings of
         * Chi\\'Ji","quality":"epic","id":152146},{"name":"Grove Darkener\\'s
         * Treads","quality":"rare","id":134429},{"name":"Sinuous Kerapteron
         * Bindings","quality":"epic","id":152087},{"name":"Grips of Chi\\'Ji","quality":"epic","id":152144},{"name":"Utgarde
         * Royal Signet","quality":"rare","id":133637},{"name":"Signet of the Highborne
         * Magi","quality":"rare","id":134537},{"name":"Golganneth\\'s Vitality","quality":"epic","id":154174},{"name":"Specter
         * of Betrayal","quality":"epic","id":151190},{"name":"Cloak of
         * Chi\\'Ji","quality":"epic","id":152143},{"name":"Fists of the
         * Heavens","quality":"artifact","id":128940},{"name":"Fists of the
         * Heavens","quality":"artifact","id":133948},{"name":"Guild Tabard","quality":"uncommon","id":5976}]
         * rankid : 32811625
         */

        var talents: List<TalentsEntity>? = null
        var gear: List<GearEntity>? = null


        class TeamEntity() : Parcelable {

            /**
             * name : string
             * class : 0
             * spec : 0
             */

            var teamName: String? = null//The name of the character on the team.
            @SerializedName("class")
            var teamClass: Int = 0// The class of the character on the team.
            var spec: Int = 0//The spec of the character on the team.

            constructor(parcel: Parcel) : this() {
                teamName = parcel.readString()
                teamClass = parcel.readInt()
                spec = parcel.readInt()
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(teamName)
                parcel.writeInt(teamClass)
                parcel.writeInt(spec)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<TeamEntity> {
                override fun createFromParcel(parcel: Parcel): TeamEntity {
                    return TeamEntity(parcel)
                }

                override fun newArray(size: Int): Array<TeamEntity?> {
                    return arrayOfNulls(size)
                }
            }


        }

        class TalentsEntity() : Parcelable {

            @SerializedName("name")
            var talentName: String? = null
            var id: Int = 0

            constructor(parcel: Parcel) : this() {
                talentName = parcel.readString()
                id = parcel.readInt()
            }


            companion object {
                /**
                 * name : Chi Wave
                 * id : 115098
                 */

                var TALENT_15 = 0
                var TALENT_30 = 1
                var TALENT_45 = 2
                var TALENT_60 = 3
                var TALENT_75 = 4
                var TALENT_90 = 5
                var TALENT_100 = 6


                val CREATOR: Parcelable.Creator<TalentsEntity> =
                    object : Parcelable.Creator<TalentsEntity> {
                        override fun createFromParcel(source: Parcel): TalentsEntity {
                            return TalentsEntity(source)
                        }


                        override fun newArray(size: Int): Array<TalentsEntity?> {
                            return arrayOfNulls(size)
                        }
                    }
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(talentName)
                parcel.writeInt(id)
            }

            override fun describeContents(): Int {
                return 0
            }


        }

        class GearEntity() : Parcelable {
            /**
             * name : The Wind Blows
             * quality : legendary
             * id : 151811
             */

            @SerializedName("name")
            var gearName: String? = null
            var quality: String? = null
            var id: Int = 0
            var url: String? = null

            constructor(parcel: Parcel) : this() {
                gearName = parcel.readString()
                quality = parcel.readString()
                id = parcel.readInt()
                url = parcel.readString()
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(gearName)
                parcel.writeString(quality)
                parcel.writeInt(id)
                parcel.writeString(url)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<GearEntity> {
                override fun createFromParcel(parcel: Parcel): GearEntity {
                    return GearEntity(parcel)
                }

                override fun newArray(size: Int): Array<GearEntity?> {
                    return arrayOfNulls(size)
                }
            }


        }


        override fun describeContents(): Int {
            return 0
        }


        override fun writeToParcel(dest: Parcel, flags: Int) {
            dest.writeString(this.name)
            dest.writeInt(this.total)
            dest.writeInt(this.classX)
            dest.writeInt(this.spec)
            dest.writeString(this.guild)
            dest.writeString(this.server)
            dest.writeString(this.region)
            dest.writeInt(this.duration)
            dest.writeLong(this.startTime)
            dest.writeInt(this.damageTaken)
            dest.writeInt(this.deaths)
            dest.writeInt(this.itemLevel)
            dest.writeInt(this.patch)
            dest.writeString(this.reportID)
            dest.writeInt(this.fightID)
            dest.writeInt(this.size)
            dest.writeList(this.team)
            dest.writeList(this.talents)
            dest.writeList(this.gear)
        }


        constructor() {}


        protected constructor(`in`: Parcel) {
            this.name = `in`.readString()
            this.total = `in`.readInt()
            this.classX = `in`.readInt()
            this.spec = `in`.readInt()
            this.guild = `in`.readString()
            this.server = `in`.readString()
            this.region = `in`.readString()
            this.duration = `in`.readInt()
            this.startTime = `in`.readLong()
            this.damageTaken = `in`.readInt()
            this.deaths = `in`.readInt()
            this.itemLevel = `in`.readInt()
            this.patch = `in`.readInt()
            this.reportID = `in`.readString()
            this.fightID = `in`.readInt()
            this.size = `in`.readInt()
            this.team = ArrayList()
            `in`.readList(this.team, TeamEntity::class.java.classLoader)
            this.talents = ArrayList()
            `in`.readList(this.talents, TalentsEntity::class.java.classLoader)
            this.gear = ArrayList()
            `in`.readList(this.gear, GearEntity::class.java.classLoader)
        }

        companion object {


            val CREATOR: Parcelable.Creator<RankingsEntity> =
                object : Parcelable.Creator<RankingsEntity> {
                    override fun createFromParcel(source: Parcel): RankingsEntity {
                        return RankingsEntity(source)
                    }


                    override fun newArray(size: Int): Array<RankingsEntity?> {
                        return arrayOfNulls(size)
                    }
                }
        }
    }
}
