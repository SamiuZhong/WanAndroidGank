package com.samiu.wangank.bean.base

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
sealed class WanResult<out T : Any> {

    data class Success<out T : Any>(val data: T) : WanResult<T>()
    data class Error(val msg: String) : WanResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[msg=$msg]"
        }
    }
}