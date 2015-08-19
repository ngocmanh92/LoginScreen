package com.example.myapplication;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.myapplication.R;

public class MaterialDesignActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        TextInputLayout inputLayout = (TextInputLayout) findViewById(R.id.textinputlayout_password);
        inputLayout.setErrorEnabled(true);
        inputLayout.setError("Wrong password");
    }


}
