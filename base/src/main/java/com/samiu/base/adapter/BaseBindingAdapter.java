package com.samiu.base.adapter;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * @author Samiu 2020/3/6
 */
public class BaseBindingAdapter extends BindingRecyclerViewAdapter {

    @Override
    public void onBindBinding(@NonNull ViewDataBinding binding, int variableId, int layoutRes, int position, Object item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
    }
}
