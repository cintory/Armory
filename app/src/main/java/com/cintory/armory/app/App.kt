package com.cintory.armory.app

import android.support.multidex.MultiDexApplication
import com.cintory.armory.di.component.AppComponent
import com.cintory.armory.di.component.DaggerAppComponent
import com.cintory.armory.di.module.AppModule
import com.cintory.armory.model.bean.ClassBean
import com.cintory.armory.model.bean.ZonesBean
import com.squareup.leakcanary.LeakCanary
import kotlin.properties.Delegates


/**
 * 作者：Cintory on 2018/7/23 16:22
 * 邮箱：Cintory@gmail.com
 */
class App : MultiDexApplication() {

    val mCacheManager = CacheManager()

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


    class CacheManager {
        var mClassList: List<ClassBean>? = null
        var mZonesList: List<ZonesBean>? = null

        fun getZoneByID(id: Int): ZonesBean? {
            mZonesList?.forEach { if (it.id == id) return it }
            return null
        }

        fun getEncounterID(zoneID: Int, encounterID: Int): ZonesBean.EncountersEntity? {
            val zonesBean = getZoneByID(zoneID) ?: return null
            zonesBean.encounters!!.forEach { if (it.id == encounterID) return it }
            return null
        }

        fun getClassByID(id: Int): ClassBean? {
            mClassList?.forEach { if (it.id == id) return it }
            return null
        }

        fun getSpecByID(classID: Int, specID: Int?): ClassBean.SpecsEntity? {
            specID ?: return null
            val classBean = getClassByID(classID) ?: return null
            classBean.specs!!.forEach { if (it.id == specID) return it }
            return null
        }

    }


}