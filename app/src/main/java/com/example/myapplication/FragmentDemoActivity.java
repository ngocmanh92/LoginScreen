package com.example.myapplication;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.myapplication.fragments.MyFragment;

public class FragmentDemoActivity extends ActionBarActivity implements View.OnClickListener {

    String name;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_demo);
        findViewById(R.id.button_next).setOnClickListener(this);
        findViewById(R.id.button_previous).setOnClickListener(this);

//        //TH 1
//        checkEqual("Th1", "name", null);
//        //TH 2
//        checkEqual("Th2", "name", "name");
//        //TH 3
//        checkEqual("Th3", null, "name");
    }

    private boolean checkEqual(String casensitive, String text1, String text2) {
        Log.i("FragmentDemoActivity.checkEqual", "-----> " + casensitive);
        if (text1 == text2) {
            Log.i("FragmentDemoActivity.checkEqual", "-----> == " + true);
        }

        if (text1.equals(text2)) {
            Log.i("FragmentDemoActivity.checkEqual", "-----> equal: " + true);
        }

        return false;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_next) {
            showNext();
        } else if (id == R.id.button_previous) {
            showPrevious();
        }
    }

    private void showNext() {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", "Show Next Fragment");
        myFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, myFragment);
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void showPrevious() {
        MyFragment myFragment = new MyFragment();
        Bundle bundle = new Bundle();
        bundle.putString("message", "Show Previous Fragment");
        myFragment.setArguments(bundle);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, myFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_demo, menu);
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
