package com.cintory.armory.di.module

import com.cintory.armory.BuildConfig
import com.cintory.armory.app.Constants
import com.cintory.armory.app.SecretKey
import com.cintory.armory.component.HttpCommonParamsInterceptor
import com.cintory.armory.model.http.api.WarcraftLogsApi
import com.cintory.armory.util.SystemUtil
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 作者：Cintory on 2018/7/24 18:33
 * 邮箱：Cintory@gmail.com
 */
@Module
class HttpModule {

    @Singleton
    @Provides
    internal fun provideRetrofitBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
    }


    @Singleton
    @Provides
    internal fun provideOkHttpBuilder(): OkHttpClient.Builder {
        return OkHttpClient.Builder()
    }


    @Singleton
    @Provides
    internal fun provideClient(builder: OkHttpClient.Builder): OkHttpClient {
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            builder.addInterceptor(loggingInterceptor)
        }
        val cacheFile = File(Constants.PATH_CACHE)
        val cache = Cache(cacheFile, (1024 * 1024 * 50).toLong())
        val cacheInterceptor = Interceptor { chain ->
            var request = chain.request()
            if (!SystemUtil.isNetworkConnected()) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()
            }
            val response = chain.proceed(request)
            if (SystemUtil.isNetworkConnected()) {
                val maxAge = 0
                //有网络时，不缓存
                response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
            } else {
                //无网络时，设置时间为四周
                val maxStale = 60 * 60 * 24 * 28
                response.newBuilder()
                    .header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=$maxStale"
                    )
                    .removeHeader("Pragma")
                    .build()
            }
            response
        }

        val commonParamsInterceptor = HttpCommonParamsInterceptor.Builder()
            .type(HttpCommonParamsInterceptor.Builder.Type.AUTO)
            .params("api_key", SecretKey.API_KEY)
            .build()

        //set cache
        builder.addNetworkInterceptor(cacheInterceptor)
        builder.addInterceptor(cacheInterceptor)
        builder.cache(cache)
        //set common parameter
        builder.addInterceptor(commonParamsInterceptor)
        //set timeout
        builder.connectTimeout(10, TimeUnit.SECONDS)
        builder.readTimeout(20, TimeUnit.SECONDS)
        builder.writeTimeout(20, TimeUnit.SECONDS)
        //retry
        builder.retryOnConnectionFailure(true)
        return builder.build()
    }


    @Singleton
    @Provides
    internal fun provideWarcraftLogsRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient
    ): Retrofit {
        return createRetrofit(builder, client, WarcraftLogsApi.HOST)
    }


    @Singleton
    @Provides
    internal fun provideWarcraftLogsService(
        retrofit: Retrofit
    ): WarcraftLogsApi {
        return retrofit.create(WarcraftLogsApi::class.java)
    }


    private fun createRetrofit(
        builder: Retrofit.Builder,
        client: OkHttpClient,
        url: String
    ): Retrofit {
        return builder.baseUrl(url)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}