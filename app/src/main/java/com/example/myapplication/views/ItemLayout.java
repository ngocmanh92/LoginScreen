package com.example.myapplication.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;

/**
 * Created by sondt on 24/08/2015.
 */
public class ItemLayout extends LinearLayout {
    public ItemLayout(Context context) {
        super(context);
        init(context);
    }

    public ItemLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        loadData(context, attrs);
        init(context);
    }

    public ItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadData(context,attrs);
        init(context);
    }

    String title = "";

    protected void loadData(Context context,AttributeSet atts){
        TypedArray attributes = context.getTheme().obtainStyledAttributes(atts,
                R.styleable.ItemLayout,0,0);
        try {
            title = attributes.getString(R.styleable.ItemLayout_text);
        }finally {
            attributes.recycle();
        }
    }

    ImageView imageView;
    TextView textView;
    Button button;

    protected void init(Context context){
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        LayoutInflater inflater = LayoutInflater.from(context);
//        View view = inflater.inflate(R.layout.custom_item_layout,null);
        View content = View.inflate(context, R.layout.custom_item_layout, null);
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        addView(content,params);

        imageView = (ImageView) findViewById(R.id.imageview);
        textView = (TextView) findViewById(R.id.textview);
        button = (Button) findViewById(R.id.button_action);

        textView.setText(title);
        imageView.setImageResource(R.mipmap.ic_launcher);
    }






}
