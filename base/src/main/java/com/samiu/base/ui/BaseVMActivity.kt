package com.samiu.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @author Samiu 2020/3/2
 */
abstract class BaseVMActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel
        startObserve()
        setContentView(getLayoutResId())
        initVM()
        initData()
    }

    open fun getLayoutResId(): Int = 0
    abstract fun initVM(): VM
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}