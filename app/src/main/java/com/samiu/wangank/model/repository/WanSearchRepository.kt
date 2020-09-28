package com.samiu.wangank.model.repository

import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSearchRepository : BaseWanRepository() {

    suspend fun getSearchArticle(page: Int, key: String) = readyCall{
        call(WanClient.service.searchHot(page, key))
    }

    suspend fun getHotKey() = readyCall{
        call(WanClient.service.getHot())
    }
}