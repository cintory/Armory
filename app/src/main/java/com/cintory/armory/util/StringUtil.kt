package com.cintory.armory.util

/**
 * Created by Cintory on 2018/8/3 11:23
 * Email：Cintory@gmail.com
 */
object StringUtil {

  fun formatNumber(int: Long): String {
    val double = int.toDouble()
    return when {
      int < 1000 -> int.toString()
      int < 1000000 -> String.format("%.2fK", double / 1000f)
      int < 1000000000 -> String.format("%.2fm", double / 1000000f)
      else -> {
        String.format("%.2fb", double / 1000000000f)
      }
    }
  }

  fun formatDouble(double: Double): String {
    return when {
      double < 1000 -> String.format("%.2f", double)
      double < 1000000 -> String.format("%.2fK", double / 1000)
      double < 1000000000 -> String.format("%.2m", double / 1000000)
      else -> {
        String.format("%.2b", double / 1000000000)
      }
    }
  }
}