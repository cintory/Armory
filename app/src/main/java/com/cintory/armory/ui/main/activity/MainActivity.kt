package com.cintory.armory.ui.main.activity

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.cintory.armory.R
import com.cintory.armory.app.Configuration
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.model.bean.*
import com.cintory.armory.presenter.main.MainPresenter
import com.cintory.armory.ui.main.adapter.ParameterSpannerAdapter
import com.cintory.armory.ui.main.fragment.ClassSelectFragment
import com.cintory.armory.ui.main.fragment.EncounterSelectFragment
import com.cintory.armory.util.GsonUtil
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 作者：Cintory on 2018/7/23 17:15
 * 邮箱：Cintory@gmail.com
 */
class MainActivity : BaseMvpActivity<MainPresenter, MainContract.View>(), MainContract.View {


    lateinit var mEncountersEntity: ZonesBean.EncountersEntity
    lateinit var mClass: ClassBean
    var mDifficulty: ItemBean? = null
    var mMetric: ItemBean? = null


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
                mEncountersEntity.id, mMetric?.id, "", mDifficulty!!.id,
                1.toString(), mClass.id.toString(), mClass?.specs!![0].id.toString(),
                "", 10.toString(), "", "", "", "", ""
            )
        }
    }

    override fun setContent(data: List<RankingBean>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setEncounter(encountersEntity: ZonesBean.EncountersEntity) {
        mEncountersEntity = encountersEntity
        tv_encounter_name.text = encountersEntity.name
    }

    override fun setSpec(classBean: ClassBean) {
        mClass = classBean
        if (mClass.specs == null) {
            tv_class_spec_name.text = mClass.name
        } else {
            tv_class_spec_name.text = "${mClass.name} - ${mClass.specs!![0].name}"
        }
    }
}


