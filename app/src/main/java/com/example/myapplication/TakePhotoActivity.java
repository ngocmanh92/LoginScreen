package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TakePhotoActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView imageViewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        imageViewResult = (ImageView) findViewById(R.id.imageview_result);
        findViewById(R.id.button_take_photo).setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        takePhoto();
    }

    public static final String OUTPUT_PATH = "/sdcard/Android/myphoto.png";

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri output = Uri.fromFile(new File(OUTPUT_PATH));
        intent.putExtra(MediaStore.EXTRA_OUTPUT,output);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK){
            showResult(data);
        }
    }

    private void showResult(Intent data) {
        try {
            Bitmap result = BitmapFactory.decodeStream(new FileInputStream(OUTPUT_PATH));
            imageViewResult.setImageBitmap(result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }






}
