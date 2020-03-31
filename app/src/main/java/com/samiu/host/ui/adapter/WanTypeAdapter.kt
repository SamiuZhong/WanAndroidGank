package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.SystemParent
import kotlinx.android.synthetic.main.item_wan_type.view.*

/**
 * @author Samiu 2020/3/7
 */
class WanTypeAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanTypeHolder(layoutInflater.inflate(R.layout.item_wan_type, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanTypeHolder) {
            holder.setIsRecyclable(false)
            with(holder.itemView.type_text) {
                text = list[position].name
                background = if (list[position].isSelected) {
                    setTextColor(resources.getColor(R.color.white, null))
                    resources.getDrawable(R.drawable.shape_16_3066be, null)
                } else {
                    setTextColor(resources.getColor(R.color.default_text_color_252F3B, null))
                    null
                }
            }
            holder.itemView.setOnClickListener {
                if (!list[position].isSelected) {
                    listener(list[position].id)
                    for (i in list.indices)
                        list[i].isSelected = i == position
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

class WanTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)