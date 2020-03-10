package com.samiu.host.model.http

import com.samiu.base.http.BaseRetrofitClient
import com.samiu.host.base.AppApplication

/**
 * @author Samiu 2020/3/9
 */
const val GANK_BASE_URL = "http://gank.io/api/"

object GankClient :BaseRetrofitClient(AppApplication.context, GANK_BASE_URL){
    val service by lazy { getService(GankService::class.java) }
}