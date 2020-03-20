package com.samiu.host.model.http.gank

/**
 * @author Samiu 2020/3/9
 */
sealed class GankResult<out T:Any> {

    data class Success<out T:Any>(val data:T):
        GankResult<T>()
    data class Error(val exception: Exception) : GankResult<Nothing>()


    override fun toString(): String {
        return when (this){
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}