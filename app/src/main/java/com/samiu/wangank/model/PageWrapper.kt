package com.samiu.wangank.model

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
data class PageWrapper<T>(
    val curPage: Int = 0,
    val datas: List<T> = listOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)