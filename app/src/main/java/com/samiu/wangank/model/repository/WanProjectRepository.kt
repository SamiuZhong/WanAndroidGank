package com.samiu.wangank.model.repository

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

    suspend fun getRecentProjects(page: Int): WanResult<ArticleList> = readyCall {
        call(WanClient.service.getLastedProject(page))
    }

    suspend fun getProjectType(): WanResult<List<SystemParent>> = readyCall {
        call(WanClient.service.getProjectType())
    }

    suspend fun getAllProjects(page: Int, cid: Int): WanResult<ArticleList> = readyCall {
        call(WanClient.service.getProjectTypeDetail(page, cid))
    }
}