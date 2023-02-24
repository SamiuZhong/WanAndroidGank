package com.samiu.wangank.model

/**
 * 全局Response
 *
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
data class WanResponse<T>(
    val data: T,
    val errorCode: Int = -1,
    val errorMsg: String = ""
)

