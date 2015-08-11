package com.example.myapplication;

import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class HandlerActivity extends ActionBarActivity {

    TextView textView;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Toast.makeText(HandlerActivity.this, "message: " + msg.what , Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        textView = (TextView) findViewById(R.id.textview);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.sendEmptyMessage(10);
            }
        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                textView.setText("Update by Handler");
            }
        });
        boolean postAtTime = handler.postAtTime(new Runnable() {
            @Override
            public void run() {
                textView.setText("Update by Handler after 5s");

            }
        }, SystemClock.uptimeMillis() + 5000);
        Toast.makeText(HandlerActivity.this, "postAtTime: " + postAtTime , Toast.LENGTH_LONG).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText("Update by Handler after 10s");

            }
        }, 10000);

    }


}
