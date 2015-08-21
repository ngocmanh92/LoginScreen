package com.example.myapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;

public class StyleActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);
        new MyDialog(this).show();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i("StyleActivity.onConfigurationChanged", "-------> new config");
        super.onConfigurationChanged(newConfig);
    }

    private static class MyDialog extends Dialog{

        public MyDialog(Context context) {
            super(context, R.style.StyledDialogTheme);
            setContentView(R.layout.dialog_styled);
            setTitle(R.string.title_activity_style);
        }
    }
}
