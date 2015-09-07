package com.example.myapplication.views;

import android.content.Context;
import android.hardware.Camera;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;

/**
 * Created by sondt on 07/09/2015.
 */
public class CameraPreviewView extends SurfaceView implements SurfaceHolder.Callback {

    Camera mCamera;
    SurfaceHolder mHolder;

    public CameraPreviewView(Context context, Camera camera) {
        super(context);
        this.mCamera = camera;
        mHolder = getHolder();
        mHolder.addCallback(this);
    }

    //-------------Surface Holder Callback methods-----------
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        try {
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {
        if(mHolder.getSurface() == null) return;
        //Stop preview cho chac an
        try {
            mCamera.stopPreview();
        }catch (Exception ex){}

        //start preview
        try {
            mCamera.setPreviewDisplay(mHolder);

            Camera.Size size = mCamera.getParameters().getPreviewSize();

            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.height = height;
            layoutParams.width = (int) (size.width/(float)size.height*height);
            setLayoutParams(layoutParams);

            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

    }
}
