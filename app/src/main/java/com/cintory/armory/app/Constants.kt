package com.cintory.armory.app

import java.io.File

/**
 * 作者：Cintory on 2018/7/24 18:34
 * 邮箱：Cintory@gmail.com
 */
object Constants {

  val PATH_DATA = App.instance.getCacheDir().getAbsolutePath() + File.separator + "data"

  val PATH_CACHE = PATH_DATA + "/NetCache"
}