package com.example.myapplication.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by sondt on 24/08/2015.
 */
public abstract class AbsAdapter<T> extends BaseAdapter {
    List<T> data;

    public AbsAdapter(List<T> data){
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AbsHolder holder = null;
        if(view == null){
            view = View.inflate(viewGroup.getContext(), getItemLayout(),null);
            holder = onCreateHolder(view);
            view.setTag(holder);
        }else {
            holder = (AbsHolder) view.getTag();
        }
        onBindData(i,view,viewGroup,holder);
        return view;
    }

    public abstract void onBindData(int position, View view, ViewGroup viewGroup, AbsHolder holder);

    public abstract AbsHolder onCreateHolder(View itemView);
    public abstract int getItemLayout();

    public static abstract class AbsHolder{
        protected View itemView;

        public AbsHolder(View itemView) {
            this.itemView = itemView;
        }
    }
}
