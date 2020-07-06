package com.samiu.host.model.repository

import com.samiu.host.global.NETWORK_ERROR
import com.samiu.host.model.bean.ArticleList
import com.samiu.host.model.bean.SystemParent
import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient
import com.samiu.host.model.http.WanResult

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