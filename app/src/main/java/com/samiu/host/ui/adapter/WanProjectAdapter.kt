package com.samiu.host.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.databinding.ItemWanProjectBinding
import com.samiu.host.global.toBrowser
import com.samiu.host.model.bean.Article
import com.samiu.host.ui.adapter.holder.ArticleViewHolder
import com.samiu.host.ui.adapter.holder.ProjectViewHolder
import kotlinx.android.synthetic.main.item_wan_project.view.*

/**
 * @author Samiu 2020/3/5
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanProjectAdapter(
    private val listener: WanArticleAdapter.ArticleListener
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
