package com.samiu.wangank.model.repository

import com.samiu.wangank.global.NETWORK_ERROR
import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient
import com.samiu.wangank.model.http.WanResult
import com.samiu.wangank.model.bean.ArticleList
import com.samiu.wangank.model.bean.Banner

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> {
        return readyCall(
            call = {
                call(WanClient.service.getBanner())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getHomeArticles(page))
            }, errorMessage = NETWORK_ERROR
        )
    }
}