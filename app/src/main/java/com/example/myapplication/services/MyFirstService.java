package com.example.myapplication.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

/**
 * Created by sondt on 14/08/2015.
 */
public class MyFirstService extends Service{

    public String title = "MyService";
    public class MyBinder extends Binder{
        public MyFirstService getService(){
            return MyFirstService.this;
        }
    }

    IBinder binder = new MyBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service: onCreate", Toast.LENGTH_LONG).show();
        //register broadcast receiver
        IntentFilter filter = new IntentFilter();
//        filter.addAction("com.example.myaction");
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_FULLY_REMOVED);
        filter.addDataScheme("package");
        registerReceiver(receiver,filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
        Toast.makeText(getApplicationContext(), "Service: onDestroy", Toast.LENGTH_LONG).show();;
    }
    MyBroadcastReceiver receiver = new MyBroadcastReceiver();

    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            Toast.makeText(context.getApplicationContext(),
                    "action: " + intent.getAction(),
                    Toast.LENGTH_LONG).show();;
        }
    }
}
