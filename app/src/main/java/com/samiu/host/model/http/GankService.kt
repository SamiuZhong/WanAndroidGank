package com.samiu.host.model.http

import com.samiu.host.model.bean.gank.GankBean
import com.samiu.host.model.bean.gank.GankResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author Samiu 2020/3/9
 */
interface GankService {

    @GET("data/Android/20/{page}")
    suspend fun getAndroid(@Path("page") page: Int): GankResponse<List<GankBean>>

    @GET("data/iOS/20/{page}")
    suspend fun getIos(@Path("page") page: Int): GankResponse<List<GankBean>>

    @GET("data/前端/20/{page}")
    suspend fun getFront(@Path("page") page: Int): GankResponse<List<GankBean>>

    @GET("data/拓展资源/20/{page}")
    suspend fun getExpand(@Path("page") page: Int): GankResponse<List<GankBean>>

}