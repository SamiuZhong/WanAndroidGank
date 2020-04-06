package com.samiu.base.ui

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import kotlin.reflect.KProperty

/**
 * @author Samiu 2020/4/2
 */

/**
 * Activity DataBinding Delegate
 */
fun <R : AppCompatActivity, T : ViewDataBinding> dataBinding(
    @LayoutRes layoutRes: Int
): ContentViewBindingDelegate<R, T> = ContentViewBindingDelegate(layoutRes)

class ContentViewBindingDelegate<in R : AppCompatActivity, out T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) {

    private var binding: T? = null

    operator fun getValue(activity: R, property: KProperty<*>): T {
        if (binding == null) {
            binding = DataBindingUtil.setContentView<T>(activity, layoutRes).apply {
                lifecycleOwner = activity
            }
        }
        return binding!!
    }
}