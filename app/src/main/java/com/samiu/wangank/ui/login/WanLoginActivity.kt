package com.samiu.wangank.ui.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import com.samiu.base.ui.BaseActivity
import com.samiu.base.ui.viewBinding
import com.samiu.wangank.R
import com.samiu.wangank.databinding.ActivityWanLoginBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/4/17
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanLoginActivity : BaseActivity() {

    private val binding by viewBinding(ActivityWanLoginBinding::inflate)
    private val viewModel: WanLoginViewModel by viewModel()

    override fun getBindingRoot() = binding.root

    override fun initData() = Unit

    override fun initView() {
        binding.run {
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