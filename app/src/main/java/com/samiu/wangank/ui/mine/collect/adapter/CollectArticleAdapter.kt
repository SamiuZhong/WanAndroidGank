package com.samiu.wangank.ui.mine.collect.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.samiu.wangank.bean.Article
import com.samiu.wangank.bean.ArticleDiff
import com.samiu.wangank.databinding.ItemCollectArticleBinding
import com.samiu.wangank.databinding.ItemWanArticleBinding

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
 */
class CollectArticleAdapter(
    private val listener: ArticleAdapterListener
) : PagingDataAdapter<Article, CollectArticleViewHolder>(ArticleDiff) {

    interface ArticleAdapterListener {
        fun onArticleClick(article: Article)
        fun onArticleStarChanged(article: Article, newValue: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectArticleViewHolder {
        return CollectArticleViewHolder(
            ItemCollectArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: CollectArticleViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}