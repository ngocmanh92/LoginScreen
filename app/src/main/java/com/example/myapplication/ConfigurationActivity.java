package com.example.myapplication;

import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.example.myapplication.R;
import com.example.myapplication.adapter.SimpleAdapter;

public class ConfigurationActivity extends ActionBarActivity {

    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new SimpleAdapter());
        Configuration configuration = getResources().getConfiguration();
        checkOrientation(configuration);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        checkOrientation(newConfig);
        super.onConfigurationChanged(newConfig);
    }

    private void checkOrientation(Configuration newConfig) {
        BaseAdapter adapter = (BaseAdapter) gridView.getAdapter();
        int column = 1;
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            column = 2;
        }
        gridView.setNumColumns(column);
        if(adapter != null) adapter.notifyDataSetChanged();
    }
}
