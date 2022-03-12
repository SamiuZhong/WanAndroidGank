package com.samiu.wangank.ui.system.adapter

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nex3z.flowlayout.FlowLayout
import com.samiu.base.adapter.BaseSingleRecyclerAdapter
import com.samiu.wangank.R
import com.samiu.wangank.bean.SystemParent
import com.samiu.wangank.global.CID
import com.samiu.wangank.global.TITLE
import com.samiu.wangank.ui.system.SystemDisplayActivity
import com.samiu.wangank.util.drawShape

/**
 * 体系页
 *
 * @author Samiu 2020/3/6
 * @email samiuzhong@outlook.com
 */
class WanSystemAdapter(context: Context) : BaseSingleRecyclerAdapter<SystemParent>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WanSystemHolder(layoutInflater.inflate(R.layout.item_wan_system, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is WanSystemHolder) {
            holder.setIsRecyclable(false)
            with(holder.itemView) {
                val data = mList[position]
                holder.systemTitle.text = data.name
                for (item in data.children) {
                    val textView = TextView(context).apply {
                        layoutParams = ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )
                        setPadding(20, 6, 20, 6)

                        background = drawShape(
                            context,
                            R.dimen.corner_20,
                            R.color.white,
                            R.color.reply_blue_700
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
                    holder.flowLayout.addView(textView)
                }
            }
        }
    }

    class WanSystemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var systemTitle: TextView = itemView.findViewById(R.id.system_title)
        var flowLayout: FlowLayout = itemView.findViewById(R.id.system_flow_layout)
    }
}
