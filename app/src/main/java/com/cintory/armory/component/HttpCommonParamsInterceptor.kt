package com.cintory.armory.component

import android.util.Log
import okhttp3.*
import java.util.*
import okhttp3.FormBody


/**
 * Created by Cintory on 2018/7/30 17:27
 * Email：Cintory@gmail.com
 */
class HttpCommonParamsInterceptor : Interceptor {

    val TAG = "CommonParamsInterceptor"

    lateinit var mType: Builder.Type

    lateinit var mParams: Map<String, Any>

    override fun intercept(chain: Interceptor.Chain?): Response {

        var request = chain!!.request()

        when (mType) {
            Builder.Type.HEADER -> request = addParams2Header(request, mParams)
            Builder.Type.QUERY_STRING -> request = addParams2UrlQueryString(request, mParams)
            Builder.Type.FORM -> request = addParams2FormBody(request, mParams)
            Builder.Type.AUTO -> {
                val method = request.method()
                if (method.equals("get", ignoreCase = true)) {
                    request = addParams2UrlQueryString(request, mParams)
                } else if (method.equals("post", ignoreCase = true)) {
                    request = addParams2FormBody(request, mParams)
                }
            }
            else -> throw IllegalArgumentException("unknown type")
        }
        return chain.proceed(request)
    }

    private fun addParams2Header(request: Request, mParams: Map<String, Any>): Request {

        val entrySets = mParams.entries
        val builder = request.newBuilder()
        for ((key, value) in entrySets) {
            builder.addHeader(key, value.toString())
        }
        return builder.build()
    }


    private fun addParams2UrlQueryString(request: Request, mParams: Map<String, Any>): Request {

        val httpUrl = request.url()
        val builder = httpUrl.newBuilder()
        val entrySets = mParams.entries
        for ((key, value) in entrySets) {
            builder.addQueryParameter(key, value.toString())
        }
        return request.newBuilder().url(builder.build()).build()
    }

    private fun addParams2FormBody(request: Request, mParams: Map<String, Any>): Request {

        val requestBody = request.body()
        if (requestBody is FormBody) {
            val size = requestBody.size()

            val params = TreeMap<String, String>()
            for (i in 0 until size) {
                val name = requestBody.encodedName(i)
                val value = requestBody.encodedValue(i)
                params.put(name, value)
            }


            // add mParams to params
            val entrySets = mParams.entries
            for ((key, value) in entrySets) {
                params.put(key, value.toString())
            }

            // convert params to new formBody
            val builder = FormBody.Builder()

            val newEnterySet = params.entries
            for (entry in newEnterySet) {
                builder.add(entry.key, entry.value)
            }

            // to new request
            return request.newBuilder().post(builder.build()).build()
        } else {
            Log.w(
                TAG,
                "RequestBody => " + request.body()!!.javaClass.canonicalName + " Not Support Yet!"
            )
        }


        return request
    }

    class Builder {

        var mType: Type? = null
        private val mParams: MutableMap<String, Any>


        enum class Type {

            /**
             * add params to header
             */

            HEADER,

            /**
             * add params to query string with urlencoded
             */

            QUERY_STRING,

            /**
             * add params to body according to form type
             */

            FORM,

            /**
             * only works for http GET & POST
             */

            AUTO
        }

        init {
            mParams = TreeMap()
        }

        fun type(type: Type): Builder {
            mType = type
            return this
        }

        fun params(key: String, value: String): Builder {
            mParams[key] = value
            return this
        }

        fun params(key: String, value: Int): Builder {
            mParams[key] = value
            return this
        }


        fun params(key: String, value: Double): Builder {
            mParams[key] = value
            return this
        }

        fun build(): HttpCommonParamsInterceptor {
            if (mType == null) {
                throw IllegalArgumentException("The type must be set")
            }
            val interceptor = HttpCommonParamsInterceptor()
            interceptor.mType = mType!!
            interceptor.mParams = mParams
            return interceptor
        }


    }


}