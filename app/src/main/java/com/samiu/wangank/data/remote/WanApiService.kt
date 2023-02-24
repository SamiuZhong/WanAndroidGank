package com.samiu.wangank.data.remote

import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import com.samiu.wangank.model.PageWrapper
import com.samiu.wangank.model.WanResponse
import com.samiu.wangank.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
typealias ArticleRes = WanResponse<PageWrapper<ArticleDTO>>

interface WanApiService {

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getFrontArticles(
        @Path(Constants.Network.PAGE) page: Int,
        @Query(Constants.Network.PAGE_SIZE) pageSize: Int
    ): ArticleRes

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getBanners(): WanResponse<List<BannerDTO>>

    /**
     * 广场列表数据
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticles(
        @Path(Constants.Network.PAGE) page: Int,
        @Query(Constants.Network.PAGE_SIZE) pageSize: Int
    ): ArticleRes
}