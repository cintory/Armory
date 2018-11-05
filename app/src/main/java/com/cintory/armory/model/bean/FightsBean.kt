package com.cintory.armory.model.bean

import android.os.Parcel
import android.os.Parcelable

/**
 * 作者：Cintory on 2018/7/24 17:41
 * 邮箱：Cintory@gmail.com
 */
class FightsBean {

  /**
   * title : Antorus, The Burning Throne
   * owner : Ceria
   * start : 1513793373311
   * end : 1513808187084
   * zone : 17
   */

  var lang: String? = null
  var title: String? = null
  var owner: String? = null
  var start: Long = 0
  var end: Long = 0
  var zone: Int = 0
  var fights: List<FightsEntity>? = null
  var friendlies: List<FriendliesEntity>? = null
  var enemies: List<EnemiesEntity>? = null
  var friendlyPets: List<FriendlyPetsEntity>? = null
  var enemyPets: List<EnemyPetsEntity>? = null
  var phases: List<PhasesEntity>? = null


  class FightsEntity() : Parcelable {
    /**
     * id : 1
     * start_time : 3329938
     * end_time : 3604553
     * boss : 2076
     * size : 20
     * difficulty : 5
     * kill : true
     * partial : 3
     * bossPercentage : 0
     * fightPercentage : 0
     * lastPhaseForPercentageDisplay : 1
     * name : Garothi Worldbreaker
     */

    var id: Int = 0
    var start_time: Long = 0
    var end_time: Long = 0
    var boss: Int = 0
    var size: Int = 0
    var difficulty: Int = 0
    var isKill: Boolean = false
    var partial: Int = 0
    var bossPercentage: Int = 0
    var fightPercentage: Int = 0
    var lastPhaseForPercentageDisplay: Int = 0
    var name: String? = null

    constructor(parcel: Parcel) : this() {
      id = parcel.readInt()
      start_time = parcel.readLong()
      end_time = parcel.readLong()
      boss = parcel.readInt()
      size = parcel.readInt()
      difficulty = parcel.readInt()
      isKill = parcel.readByte() != 0.toByte()
      partial = parcel.readInt()
      bossPercentage = parcel.readInt()
      fightPercentage = parcel.readInt()
      lastPhaseForPercentageDisplay = parcel.readInt()
      name = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
      parcel.writeInt(id)
      parcel.writeLong(start_time)
      parcel.writeLong(end_time)
      parcel.writeInt(boss)
      parcel.writeInt(size)
      parcel.writeInt(difficulty)
      parcel.writeByte(if (isKill) 1 else 0)
      parcel.writeInt(partial)
      parcel.writeInt(bossPercentage)
      parcel.writeInt(fightPercentage)
      parcel.writeInt(lastPhaseForPercentageDisplay)
      parcel.writeString(name)
    }

    override fun describeContents(): Int {
      return 0
    }

    companion object CREATOR : Parcelable.Creator<FightsEntity> {
      override fun createFromParcel(parcel: Parcel): FightsEntity {
        return FightsEntity(parcel)
      }

      override fun newArray(size: Int): Array<FightsEntity?> {
        return arrayOfNulls(size)
      }
    }
  }

  class FriendliesEntity {
    /**
     * name : Catherìne
     * id : 14
     * guid : 141852091
     * type : Paladin
     * fights : [{"id":1},{"id":2},{"id":3},{"id":4},{"id":5},{"id":6},{"id":7},{"id":8},{"id":9},{"id":10},{"id":11},{"id":12},{"id":13},{"id":14},{"id":15},{"id":16},{"id":17},{"id":18},{"id":19},{"id":20},{"id":22},{"id":23},{"id":24},{"id":25},{"id":26},{"id":27},{"id":28},{"id":29},{"id":30}]
     * lockBossFrame : true
     */

    var name: String? = null
    var id: Int = 0
    var guid: Int = 0
    var type: String? = null
    var isLockBossFrame: Boolean = false
    var fights: List<FriendFightsEntity>? = null


    class FriendFightsEntity {
      /**
       * id : 1
       */

      var id: Int = 0
    }
  }

  class EnemiesEntity {
    /**
     * name : Antoran Felguard
     * id : 162
     * guid : 125590
     * type : NPC
     * fights : [{"id":15,"instances":1}]
     * showBossFrame : true
     * lockBossFrame : true
     */

    var name: String? = null
    var id: Int = 0
    var guid: Int = 0
    var type: String? = null
    var isShowBossFrame: Boolean = false
    var isLockBossFrame: Boolean = false
    var fights: List<EnemyFightsEntity>? = null


    class EnemyFightsEntity {
      /**
       * id : 15
       * instances : 1
       */

      var id: Int = 0
      var instances: Int = 0
    }
  }

  class FriendlyPetsEntity {
    /**
     * name : Earthen Shield Totem
     * id : 62
     * guid : 100943
     * type : Pet
     * petOwner : 21
     * fights : [{"id":1,"instances":2},{"id":6,"instances":2},{"id":8,"instances":3},{"id":9,"instances":4},{"id":10,"instances":4},{"id":11,"instances":4},{"id":12,"instances":1},{"id":14,"instances":7}]
     */

    var name: String? = null
    var id: Int = 0
    var guid: Int = 0
    var type: String? = null
    var petOwner: Int = 0
    var fights: List<FriendlyPetsFightsEntity>? = null


    class FriendlyPetsFightsEntity {
      /**
       * id : 1
       * instances : 2
       */

      var id: Int = 0
      var instances: Int = 0
    }
  }

  class EnemyPetsEntity {
    /**
     * name : Felsilk Web
     * id : 155
     * guid : 122897
     * type : Pet
     * petOwner : 29
     * fights : [{"id":14,"instances":1}]
     */

    var name: String? = null
    var id: Int = 0
    var guid: Int = 0
    var type: String? = null
    var petOwner: Int = 0
    var fights: List<EnemtFightsEntity>? = null


    class EnemtFightsEntity {
      /**
       * id : 14
       * instances : 1
       */

      var id: Int = 0
      var instances: Int = 0
    }
  }

  class PhasesEntity {
    /**
     * boss : 2082
     * phases : ["Stage One: Attack Force","Stage Two: Contract to Kill","Stage Three: Marked
     * for Death","Stage Four: Kill Switch","Stage Five: The Perfect Weapon","Intermission: On
     * Deadly Ground"]
     */

    var boss: Int = 0
    var phases: List<String>? = null
  }
}
