package com.cintory.armory.ui.main.activity

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cintory.armory.R
import com.cintory.armory.app.Configuration
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.TableOverviewContract
import com.cintory.armory.model.bean.FightsBean
import com.cintory.armory.model.bean.RankingBean
import com.cintory.armory.model.bean.TableOverviewBean
import com.cintory.armory.presenter.main.TableOverviewPresenter
import com.cintory.armory.ui.main.adapter.TableOverviewAdapter
import kotlinx.android.synthetic.main.activity_table_overview.*


/**
 * Created by Cintory on 2018/8/2 14:20
 * Email：Cintory@gmail.com
 */
class TableOverviewActivity : BaseMvpActivity<TableOverviewPresenter, TableOverviewContract.View>(),
    TableOverviewContract.View {


  lateinit var mAdapter: TableOverviewAdapter
  private var mData = mutableListOf<TableOverviewBean>()
  private var mRankingBean: RankingBean? = null
  private var mFight: FightsBean.FightsEntity? = null
  private var mView = "damage-done"
  private val mViewList = Configuration.VIEW_CONFIG

  override fun initInject() {
    getActivityComponent().inject(this)
  }

  override fun getLayout(): Int {
    return R.layout.activity_table_overview
  }

  override fun initEventAndData() {
    mRankingBean = intent.getParcelableExtra(INTENT_KEY_RANKING_BEANS)
    mView = if (intent.getBooleanExtra(INTENT_KEY_VIEW, false)) "healing" else "damage-done"
    mAdapter = TableOverviewAdapter(mData)
    mAdapter.setOnClickListener(object : BaseAdapter.OnItemClickListener {
      override fun onItemClick(position: Int, view: View, vh: RecyclerView.ViewHolder) {
        TableDetailActivity.startActivity(
            mContext,
            mRankingBean!!.reportID,
            mFight!!,
            mAdapter.items[position]
        )
      }
    })
    rv_data.adapter = mAdapter
    rv_data.layoutManager = LinearLayoutManager(mContext)
    val dividerItemDecoration = DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)
    dividerItemDecoration.setDrawable(mContext.resources.getDrawable(R.drawable.divider_list))
    rv_data.addItemDecoration(dividerItemDecoration)
    mPresenter.getContent(mView, mRankingBean!!.reportID, mRankingBean!!.fightID)
  }

  override fun setTableData(
      data: List<TableOverviewBean>,
      fight: FightsBean.FightsEntity,
      logTime: Long
  ) {
    mFight = fight
    mAdapter.addNews(data, logTime)
  }

  companion object {
    val INTENT_KEY_RANKING_BEANS = "intent_key_ranking_beans"
    val INTENT_KEY_VIEW = "intent_key_view"

    fun startActivity(context: Context, rankingBean: RankingBean, isHealing: Boolean) {
      val intent = Intent(context, TableOverviewActivity::class.java)
      intent.putExtra(INTENT_KEY_RANKING_BEANS, rankingBean)
      intent.putExtra(INTENT_KEY_VIEW, isHealing)
      context.startActivity(intent)
    }
  }
}