package com.samiu.host.util

import android.content.Context
import android.content.SharedPreferences
import com.samiu.host.base.AppApplication
import com.samiu.host.global.WAN_ANDROID

/**
 * @author Samiu 2020/5/16
 */
@Suppress("UNCHECKED_CAST")
class SpUtil private constructor() {

    companion object {
        val getInstance: SpUtil by lazy { SpUtil() }
    }

    private val sharedPref: SharedPreferences by lazy {
        AppApplication.context.getSharedPreferences(
            WAN_ANDROID, Context.MODE_PRIVATE
        )
    }

    fun <T> putValue(name: String, value: T) = with(sharedPref.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is String -> putString(name, value)
            is Int -> putInt(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> {
                putString(name, putParce(value))
            }
        }.apply()
    }

    fun <T> getValue(name: String, default: T): T = with(sharedPref) {
        val res = when (default) {
            is Long -> getLong(name, default)
            is String -> getString(name, default)
            is Int -> getInt(name, default)
            is Boolean -> getBoolean(name, default)
            is Float -> getFloat(name, default)
            else -> {
                "error"
            }
        }
        return@with res as T
    }

    //TODO 序列化和反序列话
    private fun <E> putParce(obj: E): String {
        return ""
    }

    private fun <E> getParce(str: String): E {
        return "" as E
    }
}