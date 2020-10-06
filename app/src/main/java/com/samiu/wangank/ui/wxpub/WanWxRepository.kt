package com.samiu.wangank.ui.wxpub

import com.samiu.wangank.bean.ArticleList
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import com.samiu.wangank.bean.base.WanResult

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanWxRepository : BaseWanRepository() {

    suspend fun getWxAccount(): WanResult<List<SystemParent>> = readyCall {
        call(WanClient.service.getBlogType())
    }

    suspend fun getWxArticle(id: Int, page: Int): WanResult<ArticleList> = readyCall {
        call(WanClient.service.getBlogArticle(id, page))
    }
}