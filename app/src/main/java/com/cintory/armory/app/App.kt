package com.cintory.armory.app

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.cintory.armory.di.component.AppComponent
import com.cintory.armory.di.component.DaggerAppComponent
import com.cintory.armory.di.module.AppModule
import com.squareup.leakcanary.LeakCanary
import kotlin.properties.Delegates


/**
 * 作者：Cintory on 2018/7/23 16:22
 * 邮箱：Cintory@gmail.com
 */
class App : MultiDexApplication() {
    companion object {
        var instance: App by Delegates.notNull()
        lateinit var appComponent: AppComponent
    }

    init {
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(instance)).build()
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(instance)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return
        }
        LeakCanary.install(instance)
        // Normal app init code...
    }


}