package com.samiu.host.model.repository

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient

/**
 * @author Samiu 2020/5/20
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanCollectionRepository : BaseWanRepository() {

    suspend fun getCollections(page: Int) = readyCall(
        call = {
            call(WanClient.service.getCollectArticles(page))
        }, errorMessage = NETWORK_ERROR
    )
}