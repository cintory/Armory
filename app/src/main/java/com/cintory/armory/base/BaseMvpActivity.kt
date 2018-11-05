package com.cintory.armory.base

import com.cintory.armory.app.App
import com.cintory.armory.di.component.ActivityComponent
import com.cintory.armory.di.component.DaggerActivityComponent
import com.cintory.armory.di.module.ActivityModule
import javax.inject.Inject

/**
 * 作者：Cintory on 2018/7/23 17:52
 * 邮箱：Cintory@gmail.com
 */
abstract class BaseMvpActivity<P : BasePresenter<V>, in V : BaseView> : BaseActivity(), BaseView {

  @Inject
  lateinit var mPresenter: P


  protected fun getActivityComponent(): ActivityComponent = DaggerActivityComponent.builder()
      .appComponent(App.appComponent).activityModule(getActivityModule()).build()

  private fun getActivityModule(): ActivityModule = ActivityModule(this)

  override fun onViewCreated() {
    super.onViewCreated()
    initInject()
    mPresenter.attachView(this as V)
  }

  override fun onDestroy() {
    mPresenter.detachView()
    super.onDestroy()
  }

  protected abstract fun initInject()
}