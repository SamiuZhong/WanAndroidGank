package com.samiu.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samiu 2020/3/3
 * @github https://github.com/SamiuZhong
 * @blog samiu.top
 */
public abstract class BaseSingleRecyclerAdapter<T> extends RecyclerView.Adapter {
    private Context context;
    public LayoutInflater layoutInflater;
    public List<T> mList = new ArrayList<>();

    public BaseSingleRecyclerAdapter() {
    }

    public BaseSingleRecyclerAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 获取Item的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 添加一个Item
     *
     * @param item
     */
    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    /**
     * 添加一个Item列表
     *
     * @param items
     */
    public void addAll(List<T> items) {
        if (mList != null) {
            mList.addAll(items);
        }
        notifyDataSetChanged();
    }

    /**
     * 替换Item列表
     *
     * @param list
     */
    public void replaceAll(List<T> list) {
        this.mList.clear();
        addAll(list);
    }

    /**
     * 清空Item列表
     */
    public void clearAll() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定位置的Item
     *
     * @param index
     */
    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }
}
