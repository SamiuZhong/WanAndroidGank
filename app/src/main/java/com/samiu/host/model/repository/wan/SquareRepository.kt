package com.samiu.host.model.repository.wan

import com.samiu.host.model.bean.wan.ArticleList
import com.samiu.host.model.http.BaseWanRepository
import com.samiu.host.model.http.WanClient
import com.samiu.host.model.http.WanResult

/**
 * @author Samiu 2020/3/5
 */
class SquareRepository : BaseWanRepository() {

    suspend fun getSquareArticle(page: Int): WanResult<ArticleList> {
        return safeApiCall(
            call = {
                executeResponse(WanClient.service.getSquareArticleList(page))
            }, errorMessage = ""
        )
    }
}