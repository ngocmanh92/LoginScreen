package com.example.myapplication.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sondt_000 on 7/27/2015.
 */
public class SimpleAdapter extends BaseAdapter {
    List<String> data;
    Map<Integer,Boolean> mapChecked;

    public SimpleAdapter() {
        data = new ArrayList<String>();
        mapChecked = new HashMap<Integer, Boolean>();
        initSampleData();
    }

    private void initSampleData() {
        for (int i = 0; i < 100; i++) {
            data.add("Item " + i);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        long start = System.currentTimeMillis();
        Log.i("getView", "---->start: " + start);

        Holder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_simple_item, null);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.name.setText("Name " + position);
        holder.nickname.setText("Nick Name " + position);
        holder.checkBox.setText("Check box " + position);

//        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(false);

        if(mapChecked.containsKey(position)) {
            System.out.println("--------> " + mapChecked.get(position));
            holder.checkBox.setChecked(mapChecked.get(position));
        }

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mapChecked.put(position,isChecked);
            }
        });

        //Anh huong performance
//        int resId = position % 2 == 0 ? R.drawable.logo : R.drawable.button_normal;
//        Bitmap bitmap = BitmapFactory.decodeResource(holder.imageView.getResources(), resId);
//        holder.imageView.setImageBitmap(bitmap);

        Log.i("getView", "---->end: " + (System.currentTimeMillis() - start));

        return convertView;
    }

    private static class Holder {
        private final TextView name;
        private final TextView nickname;
        private final ImageView imageView;
        private final CheckBox checkBox;

        public View itemView;

        public Holder(View itemView) {
            this.itemView = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.imageview);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
//            int resId = position % 2 == 0 ? R.drawable.logo : R.drawable.button_normal;
//            Bitmap bitmap = BitmapFactory.decodeResource(itemView.getResources(), resId);
//            imageView.setImageBitmap(bitmap);
            name = (TextView) itemView.findViewById(R.id.textview_name);
            nickname = (TextView) itemView.findViewById(R.id.textview_nick_name);
        }
    }
}
