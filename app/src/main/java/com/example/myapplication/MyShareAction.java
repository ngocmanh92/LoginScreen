package com.example.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.ShareActionProvider;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sondt on 17/08/2015.
 */
public class MyShareAction extends ShareActionProvider {

    public MyShareAction(Context context) {
        super(context);
    }

    @Override
    public View onCreateActionView() {
        TextView textView = new TextView(getContext());
        textView.setText("My Action");
        textView.setTextColor(Color.GREEN);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "Custom Action Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        return textView;
    }
}
