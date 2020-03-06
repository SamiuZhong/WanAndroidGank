package com.samiu.host.ui.fragment.wan

import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.viewmodel.wan.WanWxViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/3/2
 */
class WanWxArticleFragment:BaseVMFragment<WanWxViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_wx_article

    private val mViewModelWan:WanWxViewModel by viewModel()

    override fun initView() = Unit

    override fun initData() = Unit
    override fun startObserve() = Unit
}