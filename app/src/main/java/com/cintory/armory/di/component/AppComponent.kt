package com.cintory.armory.di.component

import com.cintory.armory.app.App
import com.cintory.armory.di.module.AppModule
import com.cintory.armory.di.module.HttpModule
import com.cintory.armory.model.http.HttpHelper
import com.cintory.armory.model.http.RetrofitHelper
import dagger.Component
import javax.inject.Singleton

/**
 * 作者：Cintory on 2018/7/23 16:49
 * 邮箱：Cintory@gmail.com
 */
@Singleton
@Component(modules = [AppModule::class, HttpModule::class])
interface AppComponent {

  fun getContext(): App

  fun getRetrofitHelper(): RetrofitHelper

  fun getHttpHelper(): HttpHelper

}