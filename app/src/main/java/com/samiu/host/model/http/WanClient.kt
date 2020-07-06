package com.samiu.host.model.http

import com.samiu.base.http.BaseRetrofitClient
import com.samiu.host.base.AppApplication

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
const val WAN_BASE_URL = "https://www.wanandroid.com"

object WanClient : BaseRetrofitClient(AppApplication.context,
    WAN_BASE_URL
) {
    val service by lazy { getService(WanApiService::class.java) }
}