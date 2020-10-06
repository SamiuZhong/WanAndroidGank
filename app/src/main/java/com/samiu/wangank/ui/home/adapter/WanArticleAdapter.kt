package com.samiu.wangank.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.bean.ArticleDiff
import com.samiu.wangank.databinding.ItemWanArticleBinding
import com.samiu.wangank.bean.ArticleItem

/**
 * @author Samiu 2020/3/4
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanArticleAdapter(
    private val listener: ArticleAdapterListener
) : PagingDataAdapter<ArticleItem, ArticleViewHolder>(ArticleDiff) {

    interface ArticleAdapterListener {
        fun onArticleClick(article: ArticleItem)
        fun onArticleStarChanged(article: ArticleItem, newValue: Boolean)
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