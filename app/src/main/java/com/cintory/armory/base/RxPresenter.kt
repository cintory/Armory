package com.cintory.armory.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 作者：Cintory on 2018/7/23 18:32
 * 邮箱：Cintory@gmail.com
 */
open class RxPresenter<T : BaseView> : BasePresenter<T> {

  protected var mView: T? = null
  protected var mCompositeDisposable = CompositeDisposable()

  protected fun addSubscribe(subscription: Disposable) {
    mCompositeDisposable.add(subscription)
  }

  protected fun unSubscribe() {
    mCompositeDisposable.clear()
  }


  override fun attachView(view: T) {
    mView = view
  }

  override fun detachView() {
    unSubscribe()
    mView = null
  }
}