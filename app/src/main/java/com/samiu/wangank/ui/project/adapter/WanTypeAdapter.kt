package com.samiu.wangank.ui.project.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.R
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.util.drawShape
import kotlinx.android.synthetic.main.item_wan_type.view.*

/**
 * @author Samiu 2020/3/7
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanTypeAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanTypeHolder(layoutInflater.inflate(R.layout.item_wan_type, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanTypeHolder) {
            holder.setIsRecyclable(false)
            with(holder.itemView.type_text) {
                text = mList[position].name
                if (mList[position].isSelected) {
                    setTextColor(resources.getColor(R.color.white, null))
                    background = drawShape(
                        context,
                        R.dimen.corner_20,
                        R.color.reply_blue_700
                    )
                } else {
                    setTextColor(resources.getColor(R.color.default_text_color_252F3B, null))
                    background = null
                }
            }
            holder.itemView.setOnClickListener {
                if (!mList[position].isSelected) {
                    listener(mList[position].id)
                    for (i in mList.indices)
                        mList[i].isSelected = i == position
                    notifyDataSetChanged()
                }
            }
        }
    }

    private lateinit var listener: (Int) -> Unit
    fun setOnItemClick(listener: (Int) -> Unit) {
        this.listener = listener
    }

    class WanTypeHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
