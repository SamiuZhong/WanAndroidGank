package com.samiu.wangank.ui.wxpub.adapter

import android.content.Context
import com.samiu.wangank.util.toBrowser
import com.samiu.wangank.bean.Article
import com.samiu.wangank.http.BaseWanRepository
import com.samiu.wangank.http.WanClient
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

/**
 * @author Samiu 2020/7/6
 * @email samiuzhong@outlook.com
 */
class WxArticleListenerImpl(
    private val context: Context
) : WxArticleAdapter.ArticleAdapterListener, BaseWanRepository() {

    override fun onArticleClick(article: Article) {
        context.toBrowser(article.link, article.title)
    }

    override fun onArticleStarChanged(article: Article, newValue: Boolean) {
        article.collect = newValue
        if (newValue)
            collectArticle(article.id, article.originId)
        else
            cancelCollect(article.id, article.originId)
    }

    private fun collectArticle(id: Int, originId: Int) = MainScope().launch {
        if (originId != 0)
            doCollect(originId)
        else
            doCollect(id)
    }

    private fun cancelCollect(id: Int, originId: Int) = MainScope().launch {
        if (originId != 0)
            doCancelCollect(originId)
        else
            doCancelCollect(id)
    }

    private suspend fun doCollect(id: Int) = readyCall {
        call(WanClient.service.collectArticle(id))
    }

    private suspend fun doCancelCollect(id: Int) = readyCall {
        call(WanClient.service.cancelCollectArticle(id))
    }
}