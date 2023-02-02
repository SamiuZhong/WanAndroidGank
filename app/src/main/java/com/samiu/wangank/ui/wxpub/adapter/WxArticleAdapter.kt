package com.samiu.wangank.ui.wxpub.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.bean.Article
import com.samiu.wangank.databinding.ItemWxArticleBinding

/**
 * @author Samiu 2020/3/4
 * @email samiuzhong@outlook.com
 */
class WxArticleAdapter(
    private val listener: ArticleAdapterListener
) : BaseSingleRecyclerAdapter<Article>() {

    interface ArticleAdapterListener {
        fun onArticleClick(article: Article)
        fun onArticleStarChanged(article: Article, newValue: Boolean)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WxArticleViewHolder(
            ItemWxArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WxArticleViewHolder) holder.bind(mList[position])
    }
}