package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sondt_000 on 7/27/2015.
 */
public class SlowlyAdapter extends BaseAdapter {

    List<String> data;

    public SlowlyAdapter(){
        data = new ArrayList<String>();
        for(int i = 0; i < 100; i++){
            data.add("Item " + i);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_slowly_item,null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.textView.setText(getItem(position).toString());
        return convertView;
    }

    private static class Holder{
        View convertView;
        TextView textView;

        Holder(View convertView){
            this.convertView = convertView;
            textView = (TextView)convertView.findViewById(R.id.textview);
        }
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }
}
