package com.example.myapplication.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import com.example.myapplication.R;

/**
 * Created by sondt on 07/08/2015.
 */
public class UpdateDialog extends Dialog implements View.OnClickListener {

    public static interface Callback{
        public void onUpdate(int id, String name);
    }

    EditText editText;
    Callback callback;
    int id;

    public UpdateDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_update);
        editText = (EditText) findViewById(R.id.edittext_name);
        findViewById(R.id.button_update).setOnClickListener(this);
    }

    public void setCallback(Callback callback){
        this.callback = callback;
    }

    public void show(int id, String name) {
        this.id = id;
        editText.setText(name);
        show();
    }

    @Override
    public void onClick(View view) {
        if(callback != null) callback.onUpdate(id,editText.getText().toString());
    }
}
