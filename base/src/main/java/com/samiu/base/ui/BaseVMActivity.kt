package com.samiu.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @author Samiu ${DATE}
 */
abstract class BaseVMActivity<V:ViewDataBinding,VM : BaseViewModel> : AppCompatActivity() {

    lateinit var binding:V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        initView()
        initData()
        startObserve()
    }

    open fun getLayoutResId(): Int = 0
    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}