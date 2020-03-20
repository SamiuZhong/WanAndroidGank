package com.samiu.host.model.repository.wan

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.bean.wan.SystemParent
import com.samiu.host.model.http.wan.BaseWanRepository
import com.samiu.host.model.http.wan.WanClient
import com.samiu.host.model.http.wan.WanResult

/**
 * @author Samiu 2020/3/5
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