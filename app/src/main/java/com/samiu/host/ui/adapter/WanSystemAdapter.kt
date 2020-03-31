package com.samiu.host.ui.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.model.bean.SystemParent
import kotlinx.android.synthetic.main.item_wan_system.view.*

/**
 * @author Samiu 2020/3/6
 */
class WanSystemAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanSystemHolder(layoutInflater.inflate(R.layout.item_wan_system, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanSystemHolder) {
            holder.setIsRecyclable(false)
            with(holder.itemView) {
                val data = list[position]
                system_title.text = data.name
                for (item in data.children) {
                    val textView = TextView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        setPadding(10, 4, 10, 4)
                        background =
                            context?.resources?.getDrawable(R.drawable.shape_100_line_3066be, null)
                        text = item.name
                        setOnClickListener { listener(item.id, item.name) }
                    }
                    system_flow_layout.addView(textView)
                }
            }
        }
    }

    private lateinit var listener: (Int, String) -> Unit
    fun setOnItemClick(listener: (Int, String) -> Unit) {
        this.listener = listener
    }
}

class WanSystemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)