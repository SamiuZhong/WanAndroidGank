package com.samiu.host.model.repository

import com.samiu.host.model.api.BaseWanRepository
import com.samiu.host.model.api.WanClient
import com.samiu.host.model.api.WanResult
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.Banner

/**
 * @author Samiu 2020/3/3
 */
class HomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> {
        return safeApiCall(
            call = {
                executeResponse(WanClient.service.getBanner())
            }, errorMessage = ""
        )
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> {
        return safeApiCall(
            call = {
                executeResponse(WanClient.service.getHomeArticles(page))
            }, errorMessage = ""
        )
    }
}