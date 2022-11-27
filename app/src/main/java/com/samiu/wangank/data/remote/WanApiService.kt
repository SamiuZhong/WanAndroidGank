package com.samiu.wangank.data.remote

import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import com.samiu.wangank.model.PageInfo
import com.samiu.wangank.model.WanResponse
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author samiu 2022/11/26
 * @email samiuzhong@outlook.com
 */
interface WanApiService {

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(
        @Path("page") page: Int
    ): WanResponse<PageInfo<ArticleDTO>>

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getBanners(): WanResponse<List<BannerDTO>>
}