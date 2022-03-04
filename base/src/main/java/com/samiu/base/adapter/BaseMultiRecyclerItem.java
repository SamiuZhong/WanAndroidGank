package com.samiu.base.adapter;

/**
 * @author Samiu 2020/3/3
 * @email samiuzhong@outlook.com
 */
public class BaseMultiRecyclerItem {

    private Object data;
    private int type;

    public BaseMultiRecyclerItem() {
    }

    public BaseMultiRecyclerItem(Object data, int type) {
        this.data = data;
        this.type = type;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
