package com.cintory.armory.base

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

/**
 * 作者：Cintory on 2018/7/26 13:51
 * 邮箱：Cintory@gmail.com
 */
abstract class BaseDialogFragment : DialogFragment() {

    protected lateinit var mView: View
    protected val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun onDestroyView() {
        unSubscribe()
        super.onDestroyView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(
            DialogFragment.STYLE_NO_TITLE,
            android.R.style.Theme_Material_Light_Dialog_NoActionBar_MinWidth
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        mView = inflater.inflate(getLayoutID(), null)
        return mView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initInject()
        initEventAndData()
    }


    protected fun addSubscribe(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    protected fun unSubscribe() {
        mCompositeDisposable.clear()
    }

    protected abstract fun getLayoutID(): Int

    protected abstract fun initInject()

    protected abstract fun initEventAndData()


}