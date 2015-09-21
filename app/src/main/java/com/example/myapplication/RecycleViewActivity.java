package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        RecyclerView.LayoutManager manager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(gridLayoutManager);
        
        recyclerView.setAdapter(new MyAdapter(getItems()));

    }

    private static List<String> getItems() {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < 100; i++) {
            list.add("Item " + (i + 1));
        }
        return list;
    }

    private static class TextHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public TextHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }

    private static class ImageHolder extends RecyclerView.ViewHolder {

        public ImageHolder(View itemView) {
            super(itemView);
        }
    }

    private static class MyAdapter extends RecyclerView.Adapter {

        List<String> data;
        public MyAdapter(List<String> data){
            this.data = data;
        }
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            if (getItemViewType(position) == 0) {
                View itemView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.adapter_image_item,
                        null);
                return new ImageHolder(itemView);
            } else {
                View itemView = LayoutInflater.from(
                        viewGroup.getContext()).inflate(R.layout.adapter_text_item,
                        null);
                return new TextHolder(itemView);
            }

        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if(viewHolder instanceof ImageHolder){

            }else if(viewHolder instanceof TextHolder){
                TextHolder textHolder = (TextHolder)viewHolder;
                textHolder.textView.setText(data.get(position));
            }
        }


        @Override
        public int getItemViewType(int position) {
            return position%2;
        }

        @Override
        public int getItemCount() {
            return data.size();
        }
    }


}
