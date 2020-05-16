package com.samiu.host.ui.base

import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.databinding.ActivityWanPersonalBinding

/**
 * @author Samiu 2020/5/16
 */
class WanPersonalActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanPersonalBinding::inflate)

    override fun getBindingRoot() = binding.root

    override fun initView() = Unit

    override fun initData() = Unit
}