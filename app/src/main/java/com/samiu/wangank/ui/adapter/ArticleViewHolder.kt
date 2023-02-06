package com.samiu.wangank.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.samiu.wangank.databinding.ItemArticleBinding
import com.samiu.wangank.model.ArticleDTO

/**
 * @author samiu 2023/2/6
 * @email samiuzhong@outlook.com
 */
class ArticleViewHolder(
    private val binding: ItemArticleBinding,
    private val listener: ArticleAdapter.ArticleListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(article: ArticleDTO) {
        binding.articleTitle.text = article.title
    }
}