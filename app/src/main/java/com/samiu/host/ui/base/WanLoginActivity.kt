package com.samiu.host.ui.base

import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.databinding.ActivityWanLoginBinding
import com.samiu.host.viewmodel.WanLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/4/17
 */
class WanLoginActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanLoginBinding::inflate)
    private val viewModel: WanLoginViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    override fun initData() = Unit

    override fun initView() {

    }
}