package com.samiu.wangank.ui.square

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.samiu.wangank.global.DEFAULT_PAGE_SIZE
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.ui.home.HomeArticlePagingSource
import com.samiu.wangank.ui.home.WanHomeRepository

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
 */
class WanSquareRepository : BaseWanRepository() {

    fun getArticlePaging() = Pager(
        PagingConfig(
            pageSize = DEFAULT_PAGE_SIZE,
            prefetchDistance = DEFAULT_PAGE_SIZE,
        )
    ) {
        SquareArticlePagingSource()
    }.flow
}