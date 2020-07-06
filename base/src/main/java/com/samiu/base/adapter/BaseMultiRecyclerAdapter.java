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
public abstract class BaseMultiRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    public LayoutInflater layoutInflater;
    public List<BaseMultiRecyclerItem> list = new ArrayList<>();

    public BaseMultiRecyclerAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 设定Item的Type
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
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
    public void addItem(BaseMultiRecyclerItem item) {
        list.add(item);
        notifyDataSetChanged();
    }

    /**
     * 添加一个Item列表
     *
     * @param list
     */
    public void addAll(List<BaseMultiRecyclerItem> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    /**
     * 添加一个Item列表从倒数第二的位置开始，最后一个保持不变
     *
     * @param items
     */
    public void addExcludeLast(List<BaseMultiRecyclerItem> items) {
        for (BaseMultiRecyclerItem item : items) {
            list.add((list.size() - 1), item);
        }
        notifyDataSetChanged();
    }

    /**
     * 替换Item列表
     *
     * @param list
     */
    public void replaceAll(List<BaseMultiRecyclerItem> list) {
        list.clear();
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

    /**
     * 删除指定Type的所有Item
     *
     * @param item Type就是指这个item的Type
     */
    public void removeType(BaseMultiRecyclerItem item) {
        for (int i = 0; i < list.size(); i++) {
            if (item.getType() == list.get(i).getType()) {
                list.remove(i);
                i--;
            }
        }
        notifyDataSetChanged();
    }
}
