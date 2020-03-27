package com.samiu.base.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Samiu 2020/3/2
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getBindingRoot())
        initView()
        initData()
        startObserve()
    }

    abstract fun getBindingRoot(): View
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}