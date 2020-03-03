package com.samiu.base.http

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Samiu 2020/3/3
 */
class NetWorkUtils {

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val manager = context.applicationContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val info = manager.activeNetworkInfo
            return !(null == info || !info.isAvailable)
        }
    }
}