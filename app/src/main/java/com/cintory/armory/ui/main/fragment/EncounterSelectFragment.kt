package com.cintory.armory.ui.main.fragment

import android.support.v7.widget.LinearLayoutManager
import com.cintory.armory.R
import com.cintory.armory.app.App
import com.cintory.armory.base.BaseDialogFragment
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.di.component.DaggerFragmentComponent
import com.cintory.armory.di.module.FragmentModule
import com.cintory.armory.model.bean.ZonesBean
import com.cintory.armory.model.http.HttpHelper
import com.cintory.armory.ui.main.adapter.ZoneAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_select.*
import javax.inject.Inject

/**
 * 作者：Cintory on 2018/7/25 18:16
 * 邮箱：Cintory@gmail.com
 */
class EncounterSelectFragment : BaseDialogFragment() {

    @Inject
    lateinit var mHttpHelper: HttpHelper
    lateinit var mZones: ArrayList<ZonesBean>
    lateinit var mZoneAdapter: ZoneAdapter

    override fun getLayoutID() = R.layout.fragment_select

    override fun initEventAndData() {
        mZones = ArrayList()
        mZoneAdapter = ZoneAdapter(mZones)
        mZoneAdapter.mEncounterClickListener = object : ZoneAdapter.OnEncounterClickListener {
            override fun onItemClick(entity: ZonesBean.EncountersEntity) {
                if (activity is MainContract.View) {
                    (activity as MainContract.View).setEncounter(entity)
                    dismiss()
                }
            }
        }
        rv_data.layoutManager = LinearLayoutManager(context)
        rv_data.adapter = mZoneAdapter
        getEncounterList()
    }

    override fun initInject() {
        DaggerFragmentComponent.builder()
            .appComponent(App.appComponent)
            .fragmentModule(FragmentModule(this))
            .build()
            .inject(this)
    }

    private fun getEncounterList() {
        addSubscribe(
            mHttpHelper.getZone()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe { data: List<ZonesBean> ->
                    mZoneAdapter.addNews(data.filter { !it.frozen })
                }
        )
    }

    companion object {
        fun newInstance(): EncounterSelectFragment {
            val fragment = EncounterSelectFragment()
            return fragment
        }
    }
}