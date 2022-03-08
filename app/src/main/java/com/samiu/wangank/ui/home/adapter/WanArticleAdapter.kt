package com.samiu.wangank.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.ArticleDiff
import com.samiu.wangank.databinding.ItemWanArticleBinding

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
 */
class WanArticleAdapter(
    private val listener: ArticleAdapterListener
) : PagingDataAdapter<Article, ArticleViewHolder>(ArticleDiff) {

    interface ArticleAdapterListener {
        fun onArticleClick(article: Article)
        fun onArticleStarChanged(article: Article, newValue: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemWanArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}