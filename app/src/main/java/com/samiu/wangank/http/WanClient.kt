package com.samiu.wangank.http

import com.samiu.base.http.BaseRetrofitClient
import com.samiu.wangank.base.AppApplication

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
const val WAN_BASE_URL = "https://www.wanandroid.com"

object WanClient : BaseRetrofitClient(AppApplication.context,
    WAN_BASE_URL
) {
    val service by lazy { getService(WanApiService::class.java) }
}