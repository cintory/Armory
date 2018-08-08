package com.cintory.armory.model.bean.http

/**
 * Created by Cintory on 2018/8/2 10:32
 * Email：Cintory@gmail.com
 */
class TableResponse<T>(val entries: List<T>, val totalTime: Long, val logVersion: String)