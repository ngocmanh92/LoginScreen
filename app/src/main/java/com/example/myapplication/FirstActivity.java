package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;


public class FirstActivity extends ActionBarActivity {

    public static final String PARAM_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        Button button = (Button) findViewById(R.id.button_open_second_activity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSecondActivity();
            }
        });

        Button withDataButton = (Button)findViewById(R.id.button_open_second_activity_with_data);
        withDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityWithData("Data from first Activity");
            }
        });

        Button withReceivedDataButton = (Button)findViewById(R.id.button_open_second_activity_with_received_data);
        withReceivedDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivityWithReceiveData("This is message from first activity");
            }
        });
    }

    private static final int REQUEST_CODE = 10;
    private void openActivityWithReceiveData(String receivedMsg){
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(PARAM_MESSAGE, receivedMsg);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            Button withReceivedDataButton = (Button)findViewById(R.id.button_open_second_activity_with_received_data);
            withReceivedDataButton.setText(data.getStringExtra("msg"));
        }
    }


    //ham nay dung de mo activity
    private void openSecondActivity(){
        Intent intent = new Intent(this,SecondActivity.class);
        startActivity(intent);
    }

    private void openActivityWithData(String msg){
        Intent intent = new Intent(this,SecondActivity.class);
        intent.putExtra(PARAM_MESSAGE, msg);

        MyObject object = new MyObject();
        object.name = "My object name";
        intent.putExtra("obj", object);

        startActivity(intent);
    }



    public static class MyObject implements Serializable{
        public String name;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
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
}
