package com.samiu.base.http

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Samiu 2020/3/3
 */
abstract class BaseRetrofitClient(var context: Context, private var baseUrl: String) {
    companion object {
        private const val DEFAULT_TIMEOUT = 20L
    }

    fun <S> getService(serviceClass: Class<S>): S {
        return Retrofit.Builder()
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(CacheInterceptor(context))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build().create(serviceClass)
    }
}