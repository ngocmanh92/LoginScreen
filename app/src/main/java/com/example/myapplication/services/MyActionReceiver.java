package com.example.myapplication.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by sondt on 14/08/2015.
 */
public class MyActionReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context.getApplicationContext(),
                intent.getAction()
                , Toast.LENGTH_SHORT).show();
    }
}
