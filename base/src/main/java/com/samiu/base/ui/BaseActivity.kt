package com.samiu.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Samiu 2020/3/2
 */
abstract class BaseActivity : AppCompatActivity(),
    CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getBindingRoot())
        initView()
        initData()
    }

    abstract fun getBindingRoot():View
    abstract fun initView()
    abstract fun initData()

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}