package com.cintory.armory.model.bean


/**
 * 作者：Cintory on 2018/7/24 18:07
 * 邮箱：Cintory@gmail.com
 */

data class TableDetailBean(
    val name: String,
    val guid: Int,
    val type: Int,
    val total: Long,
    val composite: Boolean,
    val abilityIcon: String,
    val subentries: List<Subentry>,
    val uses: Int,
    val hitCount: Int,
    val tickCount: Int,
    val tickMissCount: Int,
    val missCount: Int,
    val multistrikeHitCount: Int,
    val multistrikeTickCount: Int,
    val multistrikeMissCount: Int,
    val multistrikeTickMissCount: Int,
    val critHitCount: Int,
    val critTickCount: Int,
    val sources: List<Source>,
    val targets: List<Target>,
    val hitdetails: List<Hitdetail>,
    val multistrikedetails: List<Any>,
    val missdetails: List<Any>,
    val multistrikemissdetails: List<Any>
) {

  data class Source(
      val name: String,
      val total: Int,
      val type: String
  )

  data class Target(
      val name: String,
      val total: Int,
      val type: String
  )

  data class Hitdetail(
      val type: String,
      val total: Int,
      val count: Int,
      val absorbOrOverheal: Int,
      val min: Int,
      val max: Int
  )

  data class Subentry(
      val name: String,
      val guid: Int,
      val actor: Int,
      val type: Int,
      val actorName: String,
      val actorIcon: String,
      val actorType: String,
      val total: Int,
      val abilityIcon: String,
      val hitCount: Int,
      val tickCount: Int,
      val tickMissCount: Int,
      val missCount: Int,
      val multistrikeHitCount: Int,
      val multistrikeTickCount: Int,
      val multistrikeMissCount: Int,
      val multistrikeTickMissCount: Int,
      val critHitCount: Int,
      val critTickCount: Int,
      val sources: List<Source>,
      val targets: List<Target>,
      val hitdetails: List<Hitdetail>,
      val multistrikedetails: List<Any>,
      val missdetails: List<Any>,
      val multistrikemissdetails: List<Any>
  )
}