package com.cintory.armory.di.component

import androidx.appcompat.app.AppCompatActivity
import com.cintory.armory.di.module.ActivityModule
import com.cintory.armory.di.scope.ActivityScope
import com.cintory.armory.ui.main.activity.MainActivity
import com.cintory.armory.ui.main.activity.TableDetailActivity
import com.cintory.armory.ui.main.activity.TableOverviewActivity
import dagger.Component

/**
 * 作者：Cintory on 2018/7/23 18:12
 * 邮箱：Cintory@gmail.com
 */
@ActivityScope
@Component(dependencies = [AppComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

  fun getActivity(): AppCompatActivity

  fun inject(mainActivity: MainActivity)

  fun inject(tableOverviewActivity: TableOverviewActivity)

  fun inject(tableDetailActivity: TableDetailActivity)


}