package com.cintory.armory.model.bean

/**
 * 作者：Cintory on 2018/7/24 18:06
 * 邮箱：Cintory@gmail.com
 */
class ReportBean {
    /**
     * id : string
     * title : string
     * owner : string
     * zone : 0
     * startTime : 0
     * endTime : 0
     */

    //A UNIX timestamp with millisecond precision, indicating the end time of the report as far as logged events.


    var id: String? = null
    //The report code. Can be viewed on the site with the URL string of /reports/<code>.
    var title: String? = null//The title for the report.
    var owner: String? = null//The name of the user that uploaded the report.
    var zone: Int = 0
    // The zone that the report is for. A value of -1 indicates that the zone for the report is not known.
    var startTime: Int = 0
    //A UNIX timestamp with millisecond precision, indicating the start time of the report as far as logged events.
    var endTime: Int = 0
}
