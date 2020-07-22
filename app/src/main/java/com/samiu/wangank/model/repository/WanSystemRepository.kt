package com.samiu.wangank.model.repository

import com.samiu.wangank.global.NETWORK_ERROR
import com.samiu.wangank.model.bean.ArticleList
import com.samiu.wangank.model.bean.SystemParent
import com.samiu.wangank.model.http.BaseWanRepository
import com.samiu.wangank.model.http.WanClient
import com.samiu.wangank.model.http.WanResult

/**
 * @author Samiu 2020/3/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSystemRepository : BaseWanRepository() {

    suspend fun getSystem(): WanResult<List<SystemParent>> {
        return readyCall(
            call = {
                call(WanClient.service.getSystemType())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getSystemDetail(page: Int, cid: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getSystemTypeDetail(page, cid))
            }, errorMessage = NETWORK_ERROR
        )
    }
}