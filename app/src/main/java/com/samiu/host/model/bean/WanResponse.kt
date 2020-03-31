package com.samiu.host.model.bean

/**
 * @author Samiu 2020/3/3
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T)