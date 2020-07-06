package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.SystemParent
import kotlinx.android.synthetic.main.item_wan_verti_title.view.*

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
            with(holder.itemView.title_text) {
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

    class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
