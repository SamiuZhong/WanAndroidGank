package com.samiu.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Samiu 2020/3/2
 * @email samiuzhong@outlook.com
 */
abstract class BaseActivity : AppCompatActivity(),
    CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getBindingRoot())
        initView()
        initData()
        startObserve()
    }

    abstract fun getBindingRoot():View
    abstract fun initView()
    abstract fun initData()
    open fun startObserve()=Unit

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}