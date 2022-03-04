package com.samiu.wangank.ui.home

import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.bean.base.WanResult

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> = readyCall{
        call(WanClient.service.getBanner())
    }

    suspend fun getArticlesList(page: Int): WanResult<ArticleList> = readyCall{
        call(WanClient.service.getHomeArticles(page))
    }
}