package com.samiu.wangank.ui.search

import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient

/**
 * @author Samiu 2020/3/7
 * @email samiuzhong@outlook.com
 */
class WanSearchRepository : BaseWanRepository() {

    suspend fun getSearchArticle(page: Int, key: String) = readyCall{
        call(WanClient.service.searchHot(page, key))
    }

    suspend fun getHotKey() = readyCall{
        call(WanClient.service.getHot())
    }
}