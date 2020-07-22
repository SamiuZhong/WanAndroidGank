package com.samiu.wangank.ui.base

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.ItemTouchHelper
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityWanPersonalBinding
import com.samiu.wangank.global.USER_NAME
import com.samiu.wangank.global.drawShape
import com.samiu.wangank.ui.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.adapter.WanArticleAdapter
import com.samiu.wangank.util.Preference
import com.samiu.wangank.viewmodel.WanPersonalViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

/**
 * @author Samiu 2020/5/16
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanPersonalActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanPersonalBinding::inflate)
    private val viewModel: WanPersonalViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    private val userName: String by Preference(USER_NAME, "")
    private var mCurrentPage by Delegates.notNull<Int>()
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.nickname.text = userName
        initAdapter()
        initRefresh()
        setLogout()
    }

    override fun initData() {
        binding.refreshLayout.autoRefresh()
    }

    override fun startObserve() = viewModel.run {
        mCollections.observe(this@WanPersonalActivity, Observer {
            for (data in it) {
                data.collect = true
            }
            mAdapter.addAll(it)
        })
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(this))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }

    private fun initRefresh() {
        with(binding.refreshLayout) {
            setOnRefreshListener {
                mCurrentPage = 0
                mAdapter.clearAll()
                viewModel.getCollections(mCurrentPage)
                finishRefresh(1000)
            }
            setOnLoadMoreListener {
                mCurrentPage += 1
                viewModel.getCollections(mCurrentPage)
                finishLoadMore(1000)
            }
        }
    }

    private fun setLogout() {
        binding.logOutBtn.run {
            background = drawShape(
                this@WanPersonalActivity,
                10F,
                getColor(R.color.reply_blue_700)
            )
            setOnClickListener {
                MaterialDialog(this@WanPersonalActivity).show {
                    message(R.string.confirm_log_out)
                    positiveButton(R.string.confirm) { logout() }
                    negativeButton(R.string.cancel)
                    debugMode(false)
                    lifecycleOwner(this@WanPersonalActivity)
                }
            }
        }
    }

    private fun logout() {
        viewModel.logout()
        finish()
    }
}