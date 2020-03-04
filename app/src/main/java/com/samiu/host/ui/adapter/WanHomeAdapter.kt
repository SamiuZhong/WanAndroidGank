package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.wan.Article
import kotlinx.android.synthetic.main.item_wan_home.view.*

/**
 * @author Samiu 2020/3/4
 */
class WanHomeAdapter(context: Context?) : BaseSingleRecyclerAdapter<Article>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanHomeHolder(layoutInflater.inflate(R.layout.item_wan_home, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanHomeHolder) {
            with(holder.itemView) {
                val data = list[position]
                itemTitle.text = data.title
                itemNiceData.text = data.niceDate
                itemAuthor.text = data.shareUser
            }
        }
    }
}

class WanHomeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)