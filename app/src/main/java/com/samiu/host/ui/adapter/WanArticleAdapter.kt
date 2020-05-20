package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.Article
import kotlinx.android.synthetic.main.item_wan_article.view.*

/**
 * @author Samiu 2020/3/4
 */
class WanArticleAdapter(context: Context?) : BaseSingleRecyclerAdapter<Article>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanHomeHolder(
            layoutInflater.inflate(
                R.layout.item_wan_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanHomeHolder)
            with(holder.itemView) {
                val data = list[position]
                item_title.text = data.title
                item_nice_data.text = data.niceDate
                item_author.text = data.shareUser
                setOnClickListener { listener(data.link) }
            }
    }

    private lateinit var listener: (String) -> Unit

    fun setOnItemClick(listener: (String) -> Unit) {
        this.listener = listener
    }

    class WanHomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
