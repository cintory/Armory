package com.cintory.armory.di.module

import android.app.Activity
import com.cintory.armory.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

/**
 * 作者：Cintory on 2018/7/23 18:13
 * 邮箱：Cintory@gmail.com
 */
@Module
class ActivityModule(var mActivity: Activity) {

    @Provides
    @ActivityScope
    fun provideActivity() = mActivity

}