package com.samiu.host.ui.base

import android.widget.Toast
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

    private val binding by viewBinding(ActivityWanLoginBinding::inflate)
    private val viewModel: WanLoginViewModel by viewModel()
    override fun getBindingRoot() = binding.root

    override fun initData() = Unit

    override fun initView() {
        binding.run {
            cancelBtn.setOnClickListener { finish() }
            loginBtn.setOnClickListener {
                if (userName.text.isNullOrBlank() && password.text!!.isNotBlank())
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
}