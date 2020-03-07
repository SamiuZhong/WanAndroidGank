package com.samiu.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samiu 2020/3/3
 */
public abstract class BaseSingleRecyclerAdapter<T> extends RecyclerView.Adapter {
    private Context context;
    public LayoutInflater layoutInflater;
    public List<T> list = new ArrayList<>();

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
        return list.size();
    }

    /**
     * 添加一个Item
     *
     * @param item
     */
    public void addItem(T item) {
        list.add(item);
        notifyDataSetChanged();
    }

    /**
     * 添加一个Item列表
     *
     * @param items
     */
    public void addAll(List<T> items) {
        if (list != null) {
            list.addAll(items);
        }
        notifyDataSetChanged();
    }

    /**
     * 替换Item列表
     *
     * @param list
     */
    public void replaceAll(List<T> list) {
        this.list.clear();
        addAll(list);
    }

    /**
     * 清空Item列表
     */
    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    /**
     * 删除指定位置的Item
     *
     * @param index
     */
    public void remove(int index) {
        list.remove(index);
        notifyDataSetChanged();
    }
}
