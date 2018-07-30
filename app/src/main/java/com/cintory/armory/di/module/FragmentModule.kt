package com.cintory.armory.di.module

import android.support.v4.app.Fragment
import com.cintory.armory.di.scope.FragmentScope
import dagger.Module
import dagger.Provides

/**
 * 作者：Cintory on 2018/7/23 18:51
 * 邮箱：Cintory@gmail.com
 */
@Module
class FragmentModule(var mFragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideActivity() = mFragment.context
}
