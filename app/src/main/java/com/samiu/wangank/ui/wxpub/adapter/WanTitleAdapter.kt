package com.samiu.wangank.ui.wxpub.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.R
import com.samiu.wangank.bean.SystemParent

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanTitleAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TitleHolder(layoutInflater.inflate(R.layout.item_wan_verti_title, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TitleHolder) {
            val data = mList[position]
            with(holder.titleTv) {
                text = data.name
                if (data.isSelected) {
                    setTextColor(resources.getColor(R.color.reply_blue_700, null))
                    background = resources.getDrawable(R.color.reply_blue_50, null)
                } else {
                    setTextColor(resources.getColor(R.color.default_prompt_text_color_9496A4, null))
                    background = resources.getDrawable(R.color.color_F7F7F7, null)
                }
            }
            holder.itemView.setOnClickListener {
                listener(data.id)
                if (!data.isSelected) {
                    for (index in mList.indices)
                        mList[index].isSelected = index == position
                    notifyDataSetChanged()
                }
            }
        }
    }

    private lateinit var listener: (Int) -> Unit
    fun setOnItemClick(listener: (Int) -> Unit) {
        this.listener = listener
    }

    class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleTv: TextView = itemView.findViewById(R.id.title_text)
    }
}
