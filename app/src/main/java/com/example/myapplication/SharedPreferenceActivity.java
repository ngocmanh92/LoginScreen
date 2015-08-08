package com.example.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferenceActivity extends ActionBarActivity implements View.OnClickListener

{

    TextView textViewValue;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        findViewById(R.id.button_save).setOnClickListener(this);
        findViewById(R.id.button_get).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edittext);
        textViewValue = (TextView) findViewById(R.id.textview_value);

    }

    @Override
    public void onClick(View view) {
       int id = view.getId();
        if(id == R.id.button_save){
            saveData();
        }else if(id == R.id.button_get){
            loadData();
        }
    }

    protected void saveData(){
        SharedPreferences preferences = getSharedPreferences("mypreference", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("data", editText.getText().toString());
        editor.commit();
    }

    protected void loadData(){
        SharedPreferences preferences = getSharedPreferences("mypreference", MODE_PRIVATE);
        String value = preferences.getString("data", "");
        textViewValue.setText(value);
    }
}
