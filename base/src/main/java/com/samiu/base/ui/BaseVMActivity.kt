package com.samiu.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Samiu 2020/3/2
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        initView()
        initData()
        startObserve()
    }

    open fun getLayoutResId(): Int = 0
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}