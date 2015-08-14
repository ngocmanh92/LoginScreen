package com.example.myapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.services.MyFirstService;

public class ServiceActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        findViewById(R.id.button_start).setOnClickListener(this);
        findViewById(R.id.button_connect).setOnClickListener(this);
        findViewById(R.id.button_disconnect).setOnClickListener(this);
        findViewById(R.id.button_stop).setOnClickListener(this);
        findViewById(R.id.button_send_data).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if(id == R.id.button_start){
            start();
        }else if(id == R.id.button_connect){
            connect();
        }else if(id == R.id.button_disconnect){
            disconnect();
        }else if(id == R.id.button_stop){
            stop();
        }else if(id == R.id.button_send_data){
            sendData();
        }
    }

    public void sendData(){
        Intent intent = new Intent("com.example.myaction");
        sendBroadcast(intent);
    }

    public void start(){
        Intent intent = new Intent(this, MyFirstService.class);
        startService(intent);
    }

    public void stop(){
        Intent intent = new Intent(this, MyFirstService.class);
        stopService(intent);
        setText("");
    }

    public void connect(){
        Intent intent = new Intent(this, MyFirstService.class);
        bindService(intent, connection, 0);
    }

    public void disconnect(){
        if(binder == null) return;

        unbindService(connection);
        binder = null;
    }
    MyFirstService.MyBinder binder;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder = (MyFirstService.MyBinder) iBinder;
            String title = binder.getService().title;

            setText(title);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            setText("Disconnected");
//            Toast.makeText(getApplicationContext(), "Disconnected", Toast.LENGTH_LONG).show();
        }
    };

    private void setText(String title) {
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText(title);
    }

}
