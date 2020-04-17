package com.samiu.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

/**
 * @author Samiu 2020/4/17
 */
abstract class BaseDBActivity:AppCompatActivity(),
    CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initData()
        startObserve()
    }

    abstract fun initView()
    abstract fun initData()
    open fun startObserve()=Unit

    override fun onDestroy() {
        super.onDestroy()
        cancel()
    }
}