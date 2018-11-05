package com.cintory.armory.ui.main.activity

import android.content.Context
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.cintory.armory.R
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.TableDetailContract
import com.cintory.armory.model.bean.FightsBean
import com.cintory.armory.model.bean.TableDetailBean
import com.cintory.armory.model.bean.TableOverviewBean
import com.cintory.armory.model.http.api.WarcraftLogsApi
import com.cintory.armory.presenter.main.TableDetailPresenter
import com.cintory.armory.ui.main.adapter.SpellCountAdapter
import com.cintory.armory.widget.glide.GlideApp
import kotlinx.android.synthetic.main.activity_ranking_detail.*

/**
 * Created by Cintory on 2018/8/1 16:08
 * Email：Cintory@gmail.com
 */
class TableDetailActivity : BaseMvpActivity<TableDetailPresenter, TableDetailContract.View>(),
    TableDetailContract.View {

  lateinit var mFight: FightsBean.FightsEntity
  lateinit var mTableOverview: TableOverviewBean
  var mReportID: String? = null
  var mSourceId: Int = 0
  var mSpellDetail = mutableListOf<TableDetailBean>()

  lateinit var mAdapter: SpellCountAdapter

  override fun initInject() {
    getActivityComponent().inject(this)
  }

  override fun getLayout(): Int {
    return R.layout.activity_ranking_detail
  }

  override fun initEventAndData() {
    mTableOverview = intent.getParcelableExtra(INTENT_KEY_OVERVIEW)
    mFight = intent.getParcelableExtra(INTENT_KEY_FIGHT)
    mReportID = intent.getStringExtra(INTENT_KEY_REPORT_ID)
    mPresenter.getTableData(
        mFight.start_time,
        mFight.end_time,
        mTableOverview.id,
        "damage-done",
        mReportID,
        mFight.id
    )
    setToolBar(tool_bar, mTableOverview.name)
    mAdapter = SpellCountAdapter(mSpellDetail)
    rv_data.layoutManager = LinearLayoutManager(mContext)
    rv_data.adapter = mAdapter
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[0].icon}"
        ).centerCrop()
        .into(iv_head)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[1].icon}"
        ).centerCrop()
        .into(iv_neck)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[2].icon}"
        ).centerCrop()
        .into(iv_shoulder)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[3].icon}"
        ).centerCrop()
        .into(iv_chest)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[4].icon}"
        ).centerCrop()
        .into(iv_waist)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[5].icon}"
        ).centerCrop()
        .into(iv_legs)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[6].icon}"
        ).centerCrop()
        .into(iv_feet)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[7].icon}"
        ).centerCrop()
        .into(iv_wrist)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[8].icon}"
        ).centerCrop()
        .into(iv_Hands)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[9].icon}"
        ).centerCrop()
        .into(iv_finger1)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[10].icon}"
        ).centerCrop()
        .into(iv_finger2)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[11].icon}"
        ).centerCrop()
        .into(iv_trinket1)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[12].icon}"
        ).centerCrop()
        .into(iv_trinket2)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[13].icon}"
        ).centerCrop()
        .into(iv_back)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[14].icon}"
        ).centerCrop()
        .into(iv_mainHand)
    GlideApp.with(mContext)
        .load(
            WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.gear[15].icon}"
        ).centerCrop()
        .into(iv_offHand)

    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[0].abilityIcon}")
        .centerCrop()
        .into(iv_talent_15)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[1].abilityIcon}")
        .centerCrop()
        .into(iv_talent_30)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[2].abilityIcon}")
        .centerCrop()
        .into(iv_talent_45)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[3].abilityIcon}")
        .centerCrop()
        .into(iv_talent_60)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[4].abilityIcon}")
        .centerCrop()
        .into(iv_talent_75)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[5].abilityIcon}")
        .centerCrop()
        .into(iv_talent_90)
    GlideApp.with(mContext)
        .load(WarcraftLogsApi.IMAGE_HOST + "img/warcraft/abilities/${mTableOverview.talents[6].abilityIcon}")
        .centerCrop()
        .into(iv_talent_100)
  }

  override fun setContent(data: List<TableDetailBean>) {
    mAdapter.addNewsCalculateMax(data)
  }


  companion object {

    val INTENT_KEY_FIGHT = "intent_key_fight"
    val INTENT_KEY_OVERVIEW = "intent_key_table_overview"
    val INTENT_KEY_REPORT_ID = "intent_key_report_id"

    fun startActivity(
        context: Context, reportID: String, fight: FightsBean.FightsEntity, tableOverviewBean:
        TableOverviewBean
    ) {
      val intent = Intent(context, TableDetailActivity::class.java)
      intent.putExtra(INTENT_KEY_FIGHT, fight)
      intent.putExtra(INTENT_KEY_OVERVIEW, tableOverviewBean)
      intent.putExtra(INTENT_KEY_REPORT_ID, reportID)
      context.startActivity(intent)
    }
  }
}