package com.cintory.armory.util

import android.content.Context
import android.net.ConnectivityManager
import com.cintory.armory.app.App

/**
 * 作者：Cintory on 2018/7/24 18:35
 * 邮箱：Cintory@gmail.com
 */
object SystemUtil {

    /**
     * 检查是否有可用网络
     */
    fun isNetworkConnected(): Boolean {
        val connectivityManager = App.instance
            .getSystemService(
                Context.CONNECTIVITY_SERVICE
            ) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }
}