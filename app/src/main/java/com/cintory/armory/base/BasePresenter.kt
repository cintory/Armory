package com.cintory.armory.base

/**
 * 作者：Cintory on 2018/7/23 17:45
 * 邮箱：Cintory@gmail.com
 */
interface BasePresenter<in T : BaseView> {

    fun attachView(view: T)

    fun detachView()
}