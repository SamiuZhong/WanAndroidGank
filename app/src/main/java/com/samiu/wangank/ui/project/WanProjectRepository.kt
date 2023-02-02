package com.samiu.wangank.ui.project

import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.bean.base.WanResult

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
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