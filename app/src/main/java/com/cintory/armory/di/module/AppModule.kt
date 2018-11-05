package com.cintory.armory.di.module

import com.cintory.armory.app.App
import com.cintory.armory.model.http.HttpHelper
import com.cintory.armory.model.http.RetrofitHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * 作者：Cintory on 2018/7/23 16:43
 * 邮箱：Cintory@gmail.com
 */
@Module
class AppModule(var mApp: App) {

  @Provides
  @Singleton
  fun provideApplicationContext(): App = mApp

  @Provides
  @Singleton
  fun provideHttpHelper(retrofitHelper: RetrofitHelper): HttpHelper = retrofitHelper

}