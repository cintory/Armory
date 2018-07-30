package com.cintory.armory.model.bean

import com.cintory.armory.model.http.api.WarcraftLogsApi

/**
 * 作者：Cintory on 2018/7/24 17:41
 * 邮箱：Cintory@gmail.com
 */
class ClassBean {
    /**
     * id : 0
     * name : string
     * specs : [{"id":0,"name":"string"}]
     */

    var id: Int = 0//A unique identifier representing this specific class.
    var name: String? = null//The English name of the class.
    var specs: List<SpecsEntity>? = null//The possible specs for this class.
    fun getClassImage(): String {
        return WarcraftLogsApi.IMAGE_HOST + "img/warcraft/icons/${this.name!!.replace(
            " ",
            ""
        )}.jpg"
    }

    inner class SpecsEntity {
        /**
         * id : 0
         * name : string
         */

        var id: Int = 0//A unique identifier representing this specific spec.
        var name: String? = null//The English name of the spec.

        fun getSpecsImage(className: String): String {
            return WarcraftLogsApi.IMAGE_HOST + "img/warcraft/icons/${className.replace(
                " ",
                ""
            )}-${this.name!!.replace(
                " ",
                ""
            )}.jpg"
        }
    }
}