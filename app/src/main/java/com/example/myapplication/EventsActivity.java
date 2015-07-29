package com.example.myapplication;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;


public class EventsActivity extends ActionBarActivity implements  CompoundButton.OnCheckedChangeListener,
                                                                    View.OnClickListener

{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        //Su kien on click
        Button button = (Button)findViewById(R.id.button_hello);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Hello ", Toast.LENGTH_SHORT).show();
            }
        });

        //su kien check change cua check box
        CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Toast.makeText(getApplicationContext(),"Hello: " + checked, Toast.LENGTH_SHORT).show();
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton) radioGroup.findViewById(i);
                Toast.makeText(getApplicationContext(), radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        SeekBar seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar.setMax(100);
        seekBar.setProgress(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Toast.makeText(getApplicationContext(), "onProgressChanged: " + i, Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "onStartTrackingTouch", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(getApplicationContext(), "onStopTrackingTouch", Toast.LENGTH_SHORT).show();
            }
        });

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);

        Switch switchView = (Switch) findViewById(R.id.switchview);
        switchView.setOnCheckedChangeListener(this);

        EditText editText = (EditText) findViewById(R.id.edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Toast.makeText(getApplicationContext(), "afterTextChanged: " + editable.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        LinearLayout layout = (LinearLayout) findViewById(R.id.linearlayout);
//        layout.setClickable(true);
//        layout.setFocusableInTouchMode(true);
        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getActionMasked();
//                Log.i("onTouch", "event: " + action);
                if(action == MotionEvent.ACTION_DOWN){
                    Log.i("onTouch", "event: DOWN" );
                }else if(action ==  MotionEvent.ACTION_MOVE){
                    Log.i("onTouch", "event: MOVE" );
                }else if(action == MotionEvent.ACTION_UP){
                    Log.i("onTouch", "event: UP" );
                }

                return true;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_events, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        int id = compoundButton.getId();
        if(id == R.id.switchview){

        }
    }

    @Override
    public void onClick(View view) {

    }










}
