package com.cintory.armory.util

import com.google.gson.Gson
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Created by Cintory on 2018/7/29 13:59
 * Email：Cintory@gmail.com
 */
object GsonUtil {

    fun obj2Json(obj: Any): String {
        val gson = Gson()
        return gson.toJson(obj)
    }


    fun <T> json2Obj(json: String, t: Class<T>): T {
        val gson = Gson()
        return gson.fromJson(json, t)
    }


    fun <T> json2List(json: String, t: Class<T>): MutableList<T> {
        val gson = Gson()
        val listType = ParameterizedTypeImpl(List::class.java, arrayOf(t))
        return gson.fromJson(json, listType)
    }

    class ParameterizedTypeImpl(private val raw: Class<*>, args: Array<Type>?) :
        ParameterizedType {

        private val args: Array<Type> = args ?: arrayOf()


        override fun getActualTypeArguments(): Array<out Type>? {
            return args
        }


        override fun getRawType(): Type {
            return raw
        }


        override fun getOwnerType(): Type? {
            return null
        }
    }
}
