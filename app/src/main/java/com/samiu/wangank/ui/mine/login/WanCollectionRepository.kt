package com.samiu.wangank.ui.mine.login

import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient

/**
 * @author Samiu 2020/5/20
 * @email samiuzhong@outlook.com
 */
class WanCollectionRepository : BaseWanRepository() {

    suspend fun getCollections(page: Int) = readyCall{
        call(WanClient.service.getCollectArticles(page))
    }
}