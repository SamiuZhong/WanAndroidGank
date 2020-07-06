package com.samiu.host.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.databinding.ItemWanArticleBinding
import com.samiu.host.model.bean.Article
import com.samiu.host.ui.adapter.holder.ArticleViewHolder

/**
 * @author Samiu 2020/3/4
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanArticleAdapter(
    private val listener: ArticleListener
) : BaseSingleRecyclerAdapter<Article>() {

    interface ArticleListener {
        fun onArticleClick(article: Article)
        fun onArticleStarChanged(article: Article, newValue: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ArticleViewHolder(
            ItemWanArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ArticleViewHolder) holder.bind(mList[position])
    }
}