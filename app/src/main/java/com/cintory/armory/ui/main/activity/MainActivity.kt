package com.cintory.armory.ui.main.activity

import android.animation.Animator
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cintory.armory.R
import com.cintory.armory.app.App
import com.cintory.armory.app.Configuration
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.MainContract
import com.cintory.armory.model.bean.ItemBean
import com.cintory.armory.model.bean.RankingBean
import com.cintory.armory.presenter.main.MainPresenter
import com.cintory.armory.ui.main.adapter.ParameterSpannerAdapter
import com.cintory.armory.ui.main.adapter.RankingAdapter
import com.cintory.armory.ui.main.fragment.ClassSelectFragment
import com.cintory.armory.ui.main.fragment.EncounterSelectFragment
import com.cintory.armory.util.GsonUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

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
  var mRankingList = mutableListOf<RankingBean>()

  lateinit var mRankingAdapter: RankingAdapter
  val mClassSelectFragment = ClassSelectFragment.newInstance()
  val mEncounterSelectFragment = EncounterSelectFragment.newInstance()

  override fun initInject() {
    getActivityComponent().inject(this)
  }

  override fun getLayout(): Int {
    var time = 1540957073000
    val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
    val time3 = sdf.parse("2018/10/31 11:37:53").time
    Log.e("+3", time3.toString())
    sdf.timeZone = TimeZone.getTimeZone("GTM+00:00")
    val time0 = sdf.parse("2018/10/31 11:37:53").time
    Log.e("+0", time0.toString())
    Log.e("+3 - 0 ", ((time3 - time0) / 3600000).toString())



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
    mRankingAdapter = RankingAdapter(mRankingList)
    mRankingAdapter.setOnClickListener(object : BaseAdapter.OnItemClickListener {
      override fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder) {
        TableOverviewActivity.startActivity(
            mContext, mRankingAdapter.items[position], mMetric!!.id == "hps"
        )
      }
    })
    rv_data.layoutManager = LinearLayoutManager(mContext)
    rv_data.adapter = mRankingAdapter
    fb_search_parameter.setOnClickListener {
      if (isAnimating) return@setOnClickListener
      if (cl_search_parameter.visibility == View.VISIBLE) {
        hideSearchParameterList()
      } else {
        showSearchParameterList()
      }
    }
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

  var isAnimating = false

  private fun showSearchParameterList() {
    val centerX = fb_search_parameter.x + fb_search_parameter.width / 2
    val centerY =
        (fb_search_parameter.y - cl_search_parameter.y) + fb_search_parameter.height / 2
    val finalRadius =
        Math.hypot(cl_search_parameter.width.toDouble(), cl_search_parameter.height.toDouble())
            .toFloat()
    val mCircularReveal = ViewAnimationUtils.createCircularReveal(
        cl_search_parameter,
        centerX.toInt(),
        centerY.toInt(),
        fb_search_parameter.width.toFloat() / 2, finalRadius
    )
    cl_search_parameter.visibility = View.VISIBLE
    mCircularReveal.addListener(object : Animator.AnimatorListener {
      override fun onAnimationRepeat(animation: Animator?) {
      }

      override fun onAnimationEnd(animation: Animator?) {
        isAnimating = false
      }

      override fun onAnimationCancel(animation: Animator?) {
        isAnimating = false
      }

      override fun onAnimationStart(animation: Animator?) {
        isAnimating = true
      }

    })
    mCircularReveal.start()
  }

  private fun hideSearchParameterList() {

    val centerX = fb_search_parameter.x + fb_search_parameter.width / 2
    val centerY =
        (fb_search_parameter.y - cl_search_parameter.y) + fb_search_parameter.height / 2
    val finalRadius =
        Math.hypot(cl_search_parameter.width.toDouble(), cl_search_parameter.height.toDouble())
            .toFloat()
    val mCircularReveal = ViewAnimationUtils.createCircularReveal(
        cl_search_parameter,
        centerX.toInt(),
        centerY.toInt(), finalRadius,
        fb_search_parameter.width.toFloat() / 2
    )
    mCircularReveal.addListener(object : Animator.AnimatorListener {
      override fun onAnimationRepeat(animation: Animator?) {
      }

      override fun onAnimationCancel(animation: Animator?) {
        isAnimating = false
      }

      override fun onAnimationStart(animation: Animator?) {
        isAnimating = true
      }

      override fun onAnimationEnd(animation: Animator?) {
        isAnimating = false
        cl_search_parameter.visibility = View.INVISIBLE
      }
    })
    mCircularReveal.start()
    if (mEncounterID == null || mDifficulty == null) return
    mPresenter.getRankData(
        mEncounterID!!,
        mMetric?.id,
        "",
        mDifficulty!!.id,
        1.toString(),
        mClassID.toString(),
        mSpecID.toString(),
        "",
        10.toString(),
        "",
        "",
        "",
        "",
        ""
    )
  }

}


