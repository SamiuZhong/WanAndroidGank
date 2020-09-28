package com.samiu.wangank.model.repository

import com.samiu.wangank.model.bean.ArticleList
import com.samiu.wangank.model.bean.Banner
import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient
import com.samiu.wangank.model.http.WanResult

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> = readyCall{
        call(WanClient.service.getBanner())
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> = readyCall{
        call(WanClient.service.getHomeArticles(page))
    }
}