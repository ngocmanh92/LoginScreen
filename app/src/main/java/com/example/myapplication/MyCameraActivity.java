package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.myapplication.views.CameraPreviewView;

public class MyCameraActivity extends ActionBarActivity implements View.OnClickListener, Camera.PictureCallback {

    Camera mCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);
        initCamera();
        CameraPreviewView previewView = new CameraPreviewView(this,mCamera);
        ViewGroup previewContainer = (ViewGroup) findViewById(R.id.layout_camera_container);
        previewContainer.addView(previewView);
        previewContainer.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        takePhoto();
    }

    private void takePhoto() {
        if(mCamera == null) return;
        mCamera.takePicture(null, null, this);
    }

    //--------Picture callback method----------
    @Override
    public void onPictureTaken(byte[] bytes, Camera camera) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        ImageView resultPreview = (ImageView) findViewById(R.id.imageview_result);
        resultPreview.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            mCamera.release();
        }catch (Exception ex){}
    }

    public void initCamera() {
        if (mCamera != null) return;
        try {
            mCamera = Camera.open();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



}
