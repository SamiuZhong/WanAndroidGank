package com.samiu.wangank.bean.base

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
data class WanResponse<out T>(val errorCode: Int, val errorMsg: String, val data: T?)