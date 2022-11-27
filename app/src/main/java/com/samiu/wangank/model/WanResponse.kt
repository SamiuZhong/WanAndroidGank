package com.samiu.wangank.model

/**
 * 全局Response
 *
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
data class WanResponse<T>(
    val data: T,
    val errorCode: Int = 0,
    val errorMsg: String = ""
)

