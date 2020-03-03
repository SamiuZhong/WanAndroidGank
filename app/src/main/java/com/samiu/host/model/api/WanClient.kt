package com.samiu.host.model.api

import com.samiu.base.http.BaseRetrofitClient
import com.samiu.host.AppApplication

/**
 * @author Samiu 2020/3/3
 */
const val WAN_BASE_URL = "https://www.wanandroid.com"

object WanClient : BaseRetrofitClient(AppApplication.context, WAN_BASE_URL) {
    val service by lazy { getService(WanService::class.java) }
}