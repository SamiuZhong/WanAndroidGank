package com.samiu.host.ui.fragment.wan

import com.samiu.base.ui.BaseVMFragment
import com.samiu.host.R
import com.samiu.host.viewmodel.wan.WxViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/3/2
 */
class WanWxArticleFragment:BaseVMFragment<WxViewModel>() {
    override fun getLayoutResId() = R.layout.fragment_wan_wx_article

    private val mViewModel:WxViewModel by viewModel()

    override fun initView() = Unit

    override fun initData() = Unit
    override fun startObserve() = Unit
}