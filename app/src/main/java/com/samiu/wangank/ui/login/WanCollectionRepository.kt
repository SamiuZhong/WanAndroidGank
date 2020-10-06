package com.samiu.wangank.ui.login

import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient

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