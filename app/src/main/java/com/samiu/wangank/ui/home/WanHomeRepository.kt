package com.samiu.wangank.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.bean.base.WanResult

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> = readyCall {
        call(WanClient.service.getBanner())
    }

    fun getArticlesList() = Pager(
        PagingConfig(20)
    ) {
        WanHomePagingSource(WanClient.service)
    }.flow
}