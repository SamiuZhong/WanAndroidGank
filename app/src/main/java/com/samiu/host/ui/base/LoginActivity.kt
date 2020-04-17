package com.samiu.host.ui.base

import android.widget.Toast
import com.samiu.base.ui.BaseDBActivity
import com.samiu.base.ui.dataBinding
import com.samiu.host.R
import com.samiu.host.databinding.ActivityLoginBinding
import com.samiu.host.viewmodel.WanLoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Samiu 2020/4/17
 */
class LoginActivity : BaseDBActivity() {
    override fun initData()=Unit

    private val binding: ActivityLoginBinding by dataBinding(R.layout.activity_login)
    private val viewModel: WanLoginViewModel by viewModel()

    override fun initView() {
        binding.run {
            cancelButton.setOnClickListener { finish() }
            nextButton.setOnClickListener {
                if (!inputFinished())
                    Toast.makeText(
                        this@LoginActivity,
                        getText(R.string.input_correct_info),
                        Toast.LENGTH_SHORT
                    ).show()
            }
        }
    }

    private fun inputFinished(): Boolean {
        if (binding.userName.text!!.isBlank())
            return false
        if (binding.passwordEditText.text!!.isBlank())
            return false
        return true
    }
}