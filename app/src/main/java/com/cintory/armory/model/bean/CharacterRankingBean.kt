package com.cintory.armory.model.bean

import com.google.gson.annotations.SerializedName

/**
 * 作者：Cintory on 2018/7/24 18:09
 * 邮箱：Cintory@gmail.com
 */
class CharacterRankingBean {
  /**
   * rank : 0
   * outOf : 0
   * total : 0
   * class : 0
   * spec : 0
   * guild : string
   * duration : 0
   * startTime : 0
   * itemLevel : 0
   * patch : 0
   * reportID : string
   * fightID : 0
   * difficulty : 0
   * size : 0
   * estimated : true
   */

  // Whether or not this ranking was estimated (if it was outside the cutoff limit of 500).


  var rank: Int = 0 //The rank achieved by the character.
  var outOf: Int = 0
  //The total number of parses. You can use the 'rank' and 'outOf' fields to determine a percentage.
  var total: Int = 0//For individual rankings, the DPS/HPS value.
  @SerializedName("class")
  var characterClass: Int = 0
  // For individual rankings, the class of the character.
  var spec: Int = 0//For individual rankings, the spec of the character.
  var guild: String? = null
  //The name of the guild that the character belonged to when they obtained the ranking.
  var duration: Int = 0// The length of the fight/run in milliseconds.
  var startTime: Int = 0
  //A timestamp with millisecond precision that indicates when the fight/run happened.
  var itemLevel: Int = 0
  //For fight rankings, the average item level of the raid. For character rankings, the item level of the character.
  var patch: Int = 0//For challenge mode rankings, what patch they were obtained in.
  var reportID: String? = null//The report ID that contains this ranking.
  var fightID: Int = 0//The fight ID within the report for the fight that contains this ranking.
  var difficulty: Int = 0//The difficulty setting that this rank was for.
  var size: Int = 0
  //The size of the raid. Remember that flexible raid sizes are all ranked together currently.
  var isEstimated: Boolean = false
}
