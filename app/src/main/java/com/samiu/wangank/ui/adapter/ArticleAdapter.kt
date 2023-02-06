package com.samiu.wangank.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.samiu.wangank.databinding.ItemArticleBinding
import com.samiu.wangank.model.ArticleDTO
import com.samiu.wangank.model.ArticleDiff

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
class ArticleAdapter(
    private val listener: ArticleListener
) : PagingDataAdapter<ArticleDTO, ArticleViewHolder>(ArticleDiff) {

    interface ArticleListener {
        fun onItemClick(article: ArticleDTO)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }
}