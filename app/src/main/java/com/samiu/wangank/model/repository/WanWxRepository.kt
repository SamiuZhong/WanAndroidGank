package com.samiu.wangank.model.repository

import com.samiu.wangank.global.NETWORK_ERROR
import com.samiu.wangank.model.bean.ArticleList
import com.samiu.wangank.model.bean.SystemParent
import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient
import com.samiu.wangank.model.http.WanResult

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanWxRepository : BaseWanRepository() {

    suspend fun getWxAccount(): WanResult<List<SystemParent>> {
        return readyCall(
            call = {
                call(WanClient.service.getBlogType())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getWxArticle(id: Int, page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getBlogArticle(id, page))
            }, errorMessage = NETWORK_ERROR
        )
    }
}