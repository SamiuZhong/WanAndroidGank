package com.samiu.wangank.ui.mine.personal

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityWanPersonalBinding
import com.samiu.wangank.global.USER_NAME
import com.samiu.wangank.ui.home.adapter.ArticleListenerImpl
import com.samiu.wangank.ui.home.adapter.ReboundingSwipeActionCallback
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter
import com.samiu.wangank.util.Preference
import com.samiu.wangank.util.drawShape
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * 个人中心页面
 *
 * @author Samiu 2020/5/16
 * @email samiuzhong@outlook.com
 */
class WanPersonalActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanPersonalBinding::inflate)
    private val viewModel: WanPersonalViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    private val userName: String by Preference(USER_NAME, "")
    private lateinit var mAdapter: WanArticleAdapter

    override fun initView() {
        binding.toolbar.setNavigationOnClickListener { finish() }
        binding.nickname.text = userName
        initAdapter()
        setLogout()
    }

    override fun initData() {
        lifecycleScope.launchWhenCreated {
            viewModel.articlePagingList.collectLatest {
                mAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        mAdapter = WanArticleAdapter(ArticleListenerImpl(this))
        binding.recycler.apply {
            val itemTouchHelper = ItemTouchHelper(ReboundingSwipeActionCallback())
            itemTouchHelper.attachToRecyclerView(this)
            adapter = mAdapter
        }
    }


    private fun setLogout() {
        binding.logOutBtn.run {
            background = drawShape(
                this@WanPersonalActivity,
                R.dimen.corner_5,
                R.color.reply_blue_700
            )
            setOnClickListener {
                MaterialDialog(this@WanPersonalActivity).show {
                    message(R.string.confirm_log_out)
                    positiveButton(R.string.confirm) { logout() }
                    negativeButton(R.string.cancel)
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