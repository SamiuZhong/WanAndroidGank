package com.samiu.host.model.http

/**
 * @author Samiu 2020/3/3
 */
sealed class WanResult<out  T:Any> {

    data class Success<out T : Any>(val data: T) : WanResult<T>()
    data class Error(val exception: Exception) : WanResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}