package com.cintory.armory.di.component

import android.content.Context
import com.cintory.armory.di.module.FragmentModule
import com.cintory.armory.di.scope.FragmentScope
import com.cintory.armory.ui.main.fragment.ClassSelectFragment
import com.cintory.armory.ui.main.fragment.EncounterSelectFragment
import dagger.Component

/**
 * 作者：Cintory on 2018/7/23 18:53
 * 邮箱：Cintory@gmail.com
 */
@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

  fun getContext(): Context?

  fun inject(encounterSelectFragment: EncounterSelectFragment)

  fun inject(classSelectFragment: ClassSelectFragment)
}