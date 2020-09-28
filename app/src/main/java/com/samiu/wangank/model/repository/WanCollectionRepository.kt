package com.samiu.wangank.model.repository

import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient

/**
 * @author Samiu 2020/5/20
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanCollectionRepository : BaseWanRepository() {

    suspend fun getCollections(page: Int) = readyCall{
        call(WanClient.service.getCollectArticles(page))
    }
}