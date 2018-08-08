package com.cintory.armory.ui.main.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import com.cintory.armory.R
import com.cintory.armory.base.BaseMvpActivity
import com.cintory.armory.base.contract.main.TableOverviewContract
import com.cintory.armory.model.bean.RankingBean
import com.cintory.armory.model.bean.TableOverviewBean
import com.cintory.armory.presenter.main.TableOverviewPresenter
import com.cintory.armory.ui.main.adapter.TableOverviewAdapter
import kotlinx.android.synthetic.main.activity_table_overview.*
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.RecyclerView
import android.view.View
import com.cintory.armory.base.BaseAdapter
import com.cintory.armory.model.bean.FightsBean


/**
 * Created by Cintory on 2018/8/2 14:20
 * Email：Cintory@gmail.com
 */
class TableOverviewActivity : BaseMvpActivity<TableOverviewPresenter, TableOverviewContract.View>(),
    TableOverviewContract.View {


    lateinit var mAdapter: TableOverviewAdapter
    var mData = mutableListOf<TableOverviewBean>()
    private var mRankingBean: RankingBean? = null
    private var mFight: FightsBean.FightsEntity? = null

    override fun initInject() {
        getActivityComponent().inject(this)
    }

    override fun getLayout(): Int {
        return R.layout.activity_table_overview
    }

    override fun initEventAndData() {
        mRankingBean = intent.getParcelableExtra(INTENT_KEY_RANKING_BEANS)
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
        mPresenter.getContent("damage-done", mRankingBean!!.reportID, mRankingBean!!.fightID)
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

        fun startActivity(context: Context, rankingBean: RankingBean) {
            val intent = Intent(context, TableOverviewActivity::class.java)
            intent.putExtra(INTENT_KEY_RANKING_BEANS, rankingBean)
            context.startActivity(intent)
        }
    }
}