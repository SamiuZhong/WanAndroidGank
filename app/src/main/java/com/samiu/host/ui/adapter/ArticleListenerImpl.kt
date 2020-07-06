package com.samiu.host.ui.adapter

import android.content.Context
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.Article

/**
 * @author Samiu 2020/7/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class ArticleListenerImpl(private val context: Context) : WanArticleAdapter.ArticleListener {

    override fun onArticleClick(article: Article) {
        context.toBrowser(article.link, article.title)
    }

    override fun onArticleStarChanged(article: Article, newValue: Boolean) {
        article.collect = newValue
    }
}