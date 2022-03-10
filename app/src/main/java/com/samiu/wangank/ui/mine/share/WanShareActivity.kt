package com.samiu.wangank.ui.mine.share

import com.google.android.material.snackbar.Snackbar
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityWanShareBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WanShareActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanShareBinding::inflate)
    private val viewModel: WanShareViewModel by viewModel()

    override fun getBindingRoot() = binding.root
    override fun initData() = Unit

    override fun initView() {
        binding.run {
            cancelBtn.setOnClickListener { finish() }
            loginBtn.setOnClickListener {
                viewModel.shareArticle(userName.text.toString(), password.text.toString())
            }
        }
    }

    override fun startObserve() = viewModel.run {
        shareSuccess.observe(this@WanShareActivity) {
            Snackbar.make(
                binding.loginBtn,
                getString(R.string.share_success),
                Snackbar.LENGTH_SHORT
            ).show()
        }
        shareMsg.observe(this@WanShareActivity) { msg ->
            Snackbar.make(binding.loginBtn, msg, Snackbar.LENGTH_SHORT).show()
        }
    }
}