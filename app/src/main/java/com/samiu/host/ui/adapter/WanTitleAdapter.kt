package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.wan.SystemParent
import kotlinx.android.synthetic.main.item_wan_verti_title.view.*

/**
 * @author Samiu 2020/3/7
 */
class WanTitleAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TitleHolder(layoutInflater.inflate(R.layout.item_wan_verti_title, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TitleHolder) {
            val data = list[position]
            with(holder.itemView.title_text) {
                text = data.name
                background = if (data.isSelected)
                    resources.getDrawable(R.color.white, null)
                else
                    resources.getDrawable(R.color.color_F7F7F7, null)
            }
            holder.itemView.setOnClickListener {
                listener(data.id)
                if (!data.isSelected) {
                    for (index in list.indices)
                        list[index].isSelected = index == position
                    notifyDataSetChanged()
                }
            }
        }
    }

    private lateinit var listener: (Int) -> Unit
    fun setOnItemClick(listener: (Int) -> Unit) {
        this.listener = listener
    }
}

class TitleHolder(itemView: View) : RecyclerView.ViewHolder(itemView)