package com.cintory.armory.model.bean.http

import com.cintory.armory.model.bean.RankingBean

/**
 * Created by Cintory on 2018/7/30 16:26
 * Email：Cintory@gmail.com
 */
class RankingResponse(
    val page: Int,
    val hasMorePages: Boolean,
    val count: Int,
    val rankings: List<RankingBean>
)