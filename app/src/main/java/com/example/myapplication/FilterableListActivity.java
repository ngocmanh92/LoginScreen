package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FilterableListActivity extends ActionBarActivity {

    private FilterableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filterable_list);

        ListView listView = (ListView) findViewById(R.id.listview);
        adapter = new FilterableAdapter(getData());
        listView.setAdapter(adapter);

        EditText editText = (EditText) findViewById(R.id.edittext_search);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                adapter.getFilter().filter(editable.toString());
            }
        });
    }

    private List<String> getData() {
        List<String> data = new ArrayList<String>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            data.add("Item " + random.nextInt(1000));
        }
        return data;
    }


    private static class FilterableAdapter extends BaseAdapter
            implements Filterable {
        List<String> data;
        List<String> filteredData;

        public FilterableAdapter(List<String> data) {
            this.data = data;
            filteredData = new ArrayList<String>();
            filteredData.addAll(data);
        }

        @Override
        public int getCount() {
            return filteredData.size();
        }

        @Override
        public Object getItem(int i) {
            return filteredData.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder = null;
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext())
                        .inflate(R.layout.adapter_filterable_item, null);
                holder = new Holder(view);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.textView.setText(getItem(i).toString());

            return view;
        }

        @Override
        public Filter getFilter() {
            return filter;
        }

        Filter filter = new Filter() {
            @Override
            protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                List<String> resultData = new ArrayList<String>();
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).contains(charSequence)) {
                        resultData.add(data.get(i));
                    }
                }

                Filter.FilterResults results = new Filter.FilterResults();
                results.values = resultData;
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, Filter.FilterResults
                    filterResults) {
                filteredData = (List<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };


    }

    private static class Holder {
        View itemView;
        TextView textView;

        public Holder(View itemView) {
            this.itemView = itemView;
            textView = (TextView) itemView.findViewById(R.id.textview);
        }
    }
}
