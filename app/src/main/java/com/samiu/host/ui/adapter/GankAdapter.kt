package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.gank.GankBean
import kotlinx.android.synthetic.main.item_wan_article.view.*

/**
 * @author Samiu 2020/3/9
 */
class GankAdapter(context: Context?):BaseSingleRecyclerAdapter<GankBean>(context){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GankHolder(
            layoutInflater.inflate(
                R.layout.item_wan_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is GankHolder)
            with(holder.itemView){
                val data = list[position]
                item_title.text = data.desc
                item_nice_data.text = data.publishedAt
                item_author.text = data.who
                setOnClickListener { listener(data.url) }
            }
    }

    private lateinit var listener: (String) -> Unit
    fun setOnItemClick(listener: (String) -> Unit) {
        this.listener = listener
    }
}

class GankHolder(itemView: View) : RecyclerView.ViewHolder(itemView)