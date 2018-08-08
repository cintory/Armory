package com.cintory.armory.ui.main.activity

import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.cintory.armory.R
import com.cintory.armory.app.App
import com.cintory.armory.app.Configuration
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.model.bean.*
import com.cintory.armory.presenter.main.MainPresenter
import com.cintory.armory.ui.main.adapter.ParameterSpannerAdapter
import com.cintory.armory.ui.main.adapter.RankingAdapter
import com.cintory.armory.ui.main.fragment.ClassSelectFragment
import com.cintory.armory.ui.main.fragment.EncounterSelectFragment
import com.cintory.armory.util.GsonUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：Cintory on 2018/7/23 17:15
 * 邮箱：Cintory@gmail.com
 */
class MainActivity : BaseMvpActivity<MainPresenter, MainContract.View>(), MainContract.View {

    var mZoneID: Int? = null
    var mEncounterID: Int? = null
    var mClassID: Int? = null
    var mSpecID: Int? = null

    var mDifficulty: ItemBean? = null
    var mMetric: ItemBean? = null

    lateinit var mRankingAdapter: RankingAdapter
    var mRankingList = mutableListOf<RankingBean>()

    val mClassSelectFragment = ClassSelectFragment.newInstance()
    val mEncounterSelectFragment = EncounterSelectFragment.newInstance()

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun initEventAndData() {
        tv_encounter_name.setOnClickListener {
            mEncounterSelectFragment
                .show(supportFragmentManager, "ZoneEncounterFragment")
        }

        tv_class_spec_name.setOnClickListener {
            mClassSelectFragment
                .show(supportFragmentManager, "ClassSelectFragment")
        }
        val metricList =
            GsonUtil.json2List(Configuration.METRIC_CONFIG, ItemBean::class.javaObjectType)
        val difficultyList =
            GsonUtil.json2List(Configuration.DIFFICULTY_CONFIG, ItemBean::class.javaObjectType)
        sp_metric.adapter = ParameterSpannerAdapter(mContext, metricList)
        sp_difficulty.adapter =
                ParameterSpannerAdapter(mContext, difficultyList)
        sp_metric.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (sp_metric.adapter.getItem(position) is ItemBean)
                    mMetric = sp_metric.adapter.getItem(position) as ItemBean
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }


        sp_difficulty.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (sp_difficulty.adapter.getItem(position) is ItemBean)
                    mDifficulty = sp_difficulty.adapter.getItem(position) as ItemBean
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }
        tv_search.setOnClickListener {
            mPresenter.getRankData(
                mEncounterID!!, mMetric?.id, "", mDifficulty!!.id,
                1.toString(), mClassID.toString(), mSpecID.toString(),
                "", 10.toString(), "", "", "", "", ""
            )
        }

        mRankingAdapter = RankingAdapter(mRankingList)
        rv_data.layoutManager = LinearLayoutManager(mContext)
        rv_data.adapter = mRankingAdapter
    }

    override fun setSpec(classID: Int, specID: Int?) {
        mClassID = classID
        mSpecID = specID
        val classBean = App.instance.mCacheManager.getClassByID(classID)
        val specsEntity = App.instance.mCacheManager.getSpecByID(classID, specID)
        if (specsEntity == null) {
            tv_class_spec_name.text = classBean!!.name
        } else {
            tv_class_spec_name.text = "${classBean!!.name} - ${specsEntity.name}"
        }
    }

    override fun setEncounter(zoneID: Int, encounterID: Int) {
        mZoneID = zoneID
        mEncounterID = encounterID
        tv_encounter_name.text =
                App.instance.mCacheManager.getEncounterID(zoneID, encounterID)!!.name
    }

    override fun setContent(data: List<RankingBean>) {
        mRankingAdapter.clearAndAddNews(data)
    }

}


