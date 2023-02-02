package com.samiu.base.http

import android.content.Context
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
class CacheInterceptor(var context: Context) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (NetWorkUtils.isNetworkAvailable(context)) {
            val response = chain.proceed(request)
            val maxAge = 60
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, max-age=" + maxAge)
                .build()
        } else {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .build()
            val response = chain.proceed(request)
            val maxStale = 60 * 60 * 24 * 3
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                .build();
        }
    }
}