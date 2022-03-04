package com.samiu.base.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
@SuppressLint("NotifyDataSetChanged")
abstract class BaseSingleRecyclerAdapter<T> constructor() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var context: Context
    lateinit var layoutInflater: LayoutInflater
    var mList: MutableList<T> = ArrayList()

    constructor(context: Context) : this() {
        this.context = context
        layoutInflater = LayoutInflater.from(context)
    }

    /**
     * 获取Item的数量
     *
     * @return
     */
    override fun getItemCount(): Int {
        return mList.size
    }

    /**
     * 添加一个Item
     *
     * @param item
     */
    fun addItem(item: T) {
        mList.add(item)
        notifyDataSetChanged()
    }

    /**
     * 添加一个Item列表
     *
     * @param items
     */
    fun addAll(items: List<T>) {
        mList.addAll(items)
        notifyDataSetChanged()
    }

    /**
     * 替换Item列表
     *
     * @param list
     */
    fun replaceAll(list: List<T>) {
        mList.clear()
        addAll(list)
    }

    /**
     * 清空Item列表
     */
    fun clearAll() {
        mList.clear()
        notifyDataSetChanged()
    }

    /**
     * 删除指定位置的Item
     *
     * @param index
     */
    fun remove(index: Int) {
        mList.removeAt(index)
        notifyDataSetChanged()
    }
}