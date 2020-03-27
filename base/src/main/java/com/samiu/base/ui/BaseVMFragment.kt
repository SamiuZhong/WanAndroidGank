package com.samiu.base.ui

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * @author Samiu 2020/3/2
 */
abstract class BaseVMFragment<VM : BaseViewModel>(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        initData()
        startObserve()
        super.onViewCreated(view, savedInstanceState)
    }

    abstract fun initView()
    abstract fun initData()
    abstract fun startObserve()
}