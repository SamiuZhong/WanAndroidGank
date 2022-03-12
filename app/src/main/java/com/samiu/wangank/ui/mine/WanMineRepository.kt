package com.samiu.wangank.ui.mine

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.samiu.wangank.bean.base.WanResult
import com.samiu.wangank.global.DEFAULT_PAGE_SIZE
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.ui.mine.personal.CollectArticlePagingSource
import com.samiu.wangank.ui.mine.personal.MyArticlePagingSource
import com.samiu.wangank.ui.square.SquareArticlePagingSource

/**
 * @author Samiu 2020/4/17
 * @email samiuzhong@outlook.com
 */
class WanMineRepository : BaseWanRepository() {

    /**
     * 登录
     */
    suspend fun login(userName: String, passWord: String) = readyCall {
        call(WanClient.service.login(userName, passWord))
    }

    /**
     * 登出
     */
    suspend fun logout() = readyCall {
        call(WanClient.service.logout())
    }

    /**
     * 我的收藏
     */
    fun getCollectPaging() = Pager(
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE,
        )
    ) {
        CollectArticlePagingSource()
    }.flow

    /**
     * 我分享的文章
     */
    fun getArticlePaging() = Pager(
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE,
        )
    ) {
        MyArticlePagingSource()
    }.flow

    /**
     * 分享文章
     */
    suspend fun share(title: String, link: String): WanResult<String> {
        val result: WanResult<String>?
        result = readyCall {
            call(WanClient.service.shareArticle(title, link))
        }
        return result
    }
}