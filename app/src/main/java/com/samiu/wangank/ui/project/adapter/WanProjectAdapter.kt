package com.samiu.wangank.ui.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.databinding.ItemWanProjectBinding
import com.samiu.wangank.bean.ArticleItem
import com.samiu.wangank.ui.home.adapter.WanArticleAdapter

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanProjectAdapter(
    private val listener: WanArticleAdapter.ArticleAdapterListener
) : BaseSingleRecyclerAdapter<ArticleItem>() {

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
