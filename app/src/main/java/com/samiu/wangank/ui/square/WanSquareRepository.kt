package com.samiu.wangank.ui.square

import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
 */
class WanSquareRepository : BaseWanRepository() {

    suspend fun getSquareArticle(page: Int) = readyCall{
        call(WanClient.service.getSquareArticleList(page))
    }
}