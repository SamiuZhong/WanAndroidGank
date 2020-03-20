package com.samiu.host.ui.activity

import android.content.Intent
import com.samiu.base.ui.BaseActivity
import com.samiu.host.R
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/3/20
 */
class SplashActivity :BaseActivity() {
    override fun getLayoutResId() = R.layout.activity_splash

    override fun initView() = Unit

    override fun initData() {
        startActivity(Intent(this,MainActivity::class.java))
    }

    suspend fun start(){
        MainScope().launch {
        }
    }
}