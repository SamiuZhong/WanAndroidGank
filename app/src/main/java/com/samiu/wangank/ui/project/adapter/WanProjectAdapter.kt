package com.samiu.wangank.ui.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.databinding.ItemWanProjectBinding
import com.samiu.wangank.bean.Article
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter

/**
 * @author Samiu 2020/3/5
 * @email samiuzhong@outlook.com
 */
class WanProjectAdapter(
    private val listener: WanArticleAdapter.ArticleAdapterListener
) : BaseSingleRecyclerAdapter<Article>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ProjectViewHolder(
            ItemWanProjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), listener
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ProjectViewHolder) holder.bind(mList[position])
    }
}
