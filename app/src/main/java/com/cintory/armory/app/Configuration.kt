package com.cintory.armory.app

/**
 * Created by Cintory on 2018/7/29 13:57
 * Email：Cintory@gmail.com
 */
object Configuration {
  val METRIC_CONFIG =
      "[{\"name\":\"Metric\"},{\"id\":\"dps\",\"name\":\"dps\"},{\"id\":\"hps\",\"name\":\"hps\"},{\"id\":\"bossdps\",\"name\":\"boss dps\"},{\"id\":\"tankhps\",\"name\":\"tank hps\"},{\"id\":\"krsi\",\"name\":\"krsi\"}]"
  val DIFFICULTY_CONFIG =
      "[{\"name\":\"Difficulty\"},{\"id\":1,\"name\":\"LFR\"},{\"id\":2,\"name\":\"Flex\"},{\"id\":3,\"name\":\"Normal\"},{\"id\":4,\"name\":\"Heroic\"},{\"id\":5,\"name\":\"Mythic\"}]"
//    val VIEW_CONFIG = listOf(
//        "damage-done",
//        "damage-taken",
//        "healing",
//        "casts",
//        "summons",
//        "buffs",
//        "debuffs",
//        "deaths",
//        "survivability",
//        "resources",
//        "resources-gains"
//    )

  val VIEW_CONFIG = listOf(
      "damage-done",
      "damage-taken",
      "healing",
      "casts",
      "summons",
      "buffs",
      "debuffs",
      "deaths",
      "survivability",
      "resources",
      "resources-gains"
  )
}