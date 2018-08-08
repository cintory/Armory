package com.cintory.armory.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import java.security.AccessControlContext

/**
 * 作者：Cintory on 2018/7/23 17:33
 * 邮箱：Cintory@gmail.com
 */
abstract class BaseActivity : AppCompatActivity() {

    lateinit var mContext: BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        mContext = this
        onViewCreated()
        initEventAndData()
    }

    protected fun setToolBar(toolBar: Toolbar, title: String) {
        toolBar.title = title
        setSupportActionBar(toolBar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    protected open fun onViewCreated() {}

    abstract fun getLayout(): Int

    protected abstract fun initEventAndData()
}