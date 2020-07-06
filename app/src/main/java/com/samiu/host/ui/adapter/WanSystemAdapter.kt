package com.samiu.host.ui.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.host.R
import com.samiu.host.global.CID
import com.samiu.host.global.TITLE
import com.samiu.host.global.drawShape
import com.samiu.host.model.bean.SystemParent
import com.samiu.host.ui.base.SystemDisplayActivity
import kotlinx.android.synthetic.main.item_wan_system.view.*

/**
 * @author Samiu 2020/3/6
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
class WanSystemAdapter(context: Context?) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanSystemHolder(layoutInflater.inflate(R.layout.item_wan_system, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanSystemHolder) {
            holder.setIsRecyclable(false)
            with(holder.itemView) {
                val data = mList[position]
                system_title.text = data.name
                for (item in data.children) {
                    val textView = TextView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        setPadding(20, 6, 20, 6)

                        background = drawShape(
                            context,
                            100F,
                            context.getColor(R.color.white),
                            context.getColor(R.color.reply_blue_700)
                        )

                        text = item.name
                        setOnClickListener {
                            val intent = Intent(
                                context,
                                SystemDisplayActivity::class.java
                            ).apply {
                                putExtra(CID, item.id)
                                putExtra(TITLE, item.name)
                            }
                            context.startActivity(intent)
                        }
                    }
                    system_flow_layout.addView(textView)
                }
            }
        }
    }

    class WanSystemHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
