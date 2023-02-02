package com.samiu.wangank.ui.home

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.Banner
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.global.DEFAULT_PAGE_SIZE

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
class WanHomeRepository : BaseWanRepository() {

    suspend fun getBanners(): WanResult<List<Banner>> = readyCall {
        call(WanClient.service.getBanner())
    }

    fun getArticlePaging() = Pager(
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE,
        )
    ) {
        HomeArticlePagingSource()
    }.flow
}