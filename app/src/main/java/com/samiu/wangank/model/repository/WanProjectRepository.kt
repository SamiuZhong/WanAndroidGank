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
class WanProjectRepository : BaseWanRepository() {

    suspend fun getRecentProjects(page: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getLastedProject(page))
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getProjectType(): WanResult<List<SystemParent>> {
        return readyCall(
            call = {
                call(WanClient.service.getProjectType())
            }, errorMessage = NETWORK_ERROR
        )
    }

    suspend fun getAllProjects(page: Int, cid: Int): WanResult<ArticleList> {
        return readyCall(
            call = {
                call(WanClient.service.getProjectTypeDetail(page, cid))
            }, errorMessage = NETWORK_ERROR
        )
    }
}