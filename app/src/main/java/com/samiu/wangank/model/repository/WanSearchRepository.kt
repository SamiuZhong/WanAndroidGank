package com.samiu.wangank.model.repository

import com.samiu.wangank.global.NETWORK_ERROR
import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSearchRepository : BaseWanRepository() {

    suspend fun getSearchArticle(page: Int, key: String) = readyCall(
        call = {
            call(WanClient.service.searchHot(page, key))
        }, errorMessage = NETWORK_ERROR
    )

    suspend fun getHotKey() = readyCall(
        call = {
            call(WanClient.service.getHot())
        }, errorMessage = NETWORK_ERROR
    )
}