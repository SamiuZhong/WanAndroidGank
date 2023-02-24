package com.samiu.wangank.data.remote

import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.BannerDTO
import com.samiu.wangank.model.PageWrapper
import com.samiu.wangank.model.WanResponse
import com.samiu.wangank.utils.Constants
import retrofit2.http.*

/**
 * @author samiu 2023/2/2
 * @email samiuzhong@outlook.com
 */
typealias ArticleRes = WanResponse<PageWrapper<ArticleDTO>>
typealias BannerList = List<BannerDTO>

interface WanApiService {

    /**
     * 首页banner
     */
    @GET("/banner/json")
    suspend fun getBanners(): WanResponse<BannerList>

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getFrontArticles(
        @Path(Constants.Network.PAGE) page: Int,
        @Query(Constants.Network.PAGE_SIZE) pageSize: Int
    ): ArticleRes

    /**
     * 广场列表数据
     */
    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticles(
        @Path(Constants.Network.PAGE) page: Int,
        @Query(Constants.Network.PAGE_SIZE) pageSize: Int
    ): ArticleRes

    /**
     * 搜索
     */
    @FormUrlEncoded
    @POST("/article/query/{page}/json")
    suspend fun search(
        @Path(Constants.Network.PAGE) page: Int,
        @Field(Constants.Network.PAGE_SIZE) pageSize: String,
        @Field("k") key: String
    ): ArticleRes
}