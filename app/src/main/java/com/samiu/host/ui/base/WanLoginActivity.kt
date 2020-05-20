package com.samiu.host.ui.base

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityWanLoginBinding
import com.samiu.host.viewmodel.WanLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/4/17
 */
class WanLoginActivity : BaseActivity() {

    private val mBinding by viewBinding(ActivityWanLoginBinding::inflate)
    private val viewModel: WanLoginViewModel by viewModel()

    override fun getBindingRoot() = mBinding.root

    override fun initData() = Unit

    override fun initView() {
        mBinding.run {
            cancelBtn.setOnClickListener { finish() }
            loginBtn.setOnClickListener {
                if (userName.text!!.isNotBlank() && password.text!!.isNotBlank())
                    viewModel.login(userName.text.toString(), password.text.toString())
                else
                    Toast.makeText(
                        this@WanLoginActivity,
                        getString(R.string.input_correct_info),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    override fun startObserve() = viewModel.run {
        loginSuccess.observe(this@WanLoginActivity, Observer { aBoolean ->
            if (aBoolean) {
                Toast.makeText(
                    this@WanLoginActivity,
                    getString(R.string.log_in_success),
                    Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this@WanLoginActivity, WanPersonalActivity::class.java))
                finish()
            } else
                Toast.makeText(
                    this@WanLoginActivity,
                    getString(R.string.input_correct_info),
                    Toast.LENGTH_SHORT
                ).show()
        })
    }
}