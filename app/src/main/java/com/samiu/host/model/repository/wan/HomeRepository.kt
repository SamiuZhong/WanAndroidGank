package com.samiu.host.model.repository.wan

import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient
import com.samiu.host.model.http.WanResult
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