package com.example.myapplication.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by sondt on 09/09/2015.
 */
public class ImageProcessingView extends LinearLayout {
    Paint paint;
    Path path;
    Bitmap blending;
    //Toa do ve cua blending bitmap
    float left, top;

    boolean isDrawing;
    private String TAG = ImageProcessingView.class.getName();

    public ImageProcessingView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);

        path = new Path();
        isDrawing = true;
        left = top = 0;
        bitmapX = bitmapY = 0;
    }

    public void setIsDrawing(boolean isDrawing) {
        this.isDrawing = isDrawing;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            onTouchDown(event.getX(), event.getY());
            return true;
        }
        if (event.getActionMasked() == MotionEvent.ACTION_MOVE) {
            onTouchMove(event.getX(), event.getY());
            return true;

        }
        if (event.getActionMasked() == MotionEvent.ACTION_UP) {
            onTouchUp(event.getX(), event.getY());
            return true;
        }

        return super.onTouchEvent(event);
    }

    private boolean isSelectedBitmap;
    //luu tru toa do cua blending bitmap
    private float bitmapX, bitmapY;

    private boolean isTouchedOn(float x, float y) {
        if (blending == null) return false;
//        double distance = Math.sqrt((bitmapX - x) * (bitmapX - x) + (bitmapY - y) * (bitmapY - y));
        return (x > bitmapX && y > bitmapY
                && (x - bitmapX) < blending.getWidth()
                && (y - bitmapY) < blending.getHeight());
    }

    private float mX, mY;

    protected void onTouchDown(float x, float y) {
        if (!isDrawing) {
            isSelectedBitmap = isTouchedOn(x, y);
            mX = x;
            mY = y;
            return;
        }

        path.moveTo(x, y);
        mX = x;
        mY = y;

        invalidate();
    }

    protected void onTouchUp(float x, float y) {
        if (!isDrawing) {
            moveBitmap(x, y);
            isSelectedBitmap = false;
            return;
        }
        path.lineTo(x, y);
        invalidate();
    }


    protected void onTouchMove(float x, float y) {
        if (!isDrawing) {
            moveBitmap(x, y);
            return;
        }
        path.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
        mX = x;
        mY = y;
        invalidate();
    }

    protected void moveBitmap(float x, float y) {
        if (!isSelectedBitmap) return;
        float xDistance = x - mX;
        float yDistance = y - mY;
        bitmapX += xDistance;
        bitmapY += yDistance;

        mX = x;
        mY = y;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
        if (blending != null && !blending.isRecycled()) {
            canvas.drawBitmap(blending, bitmapX, bitmapY, new Paint());
        }

    }

    public void clean() {
        path.reset();
        if (blending != null) blending.recycle();
        invalidate();
    }

    public void setBlendingBitmap(Bitmap blending) {
        if (this.blending != null) this.blending.recycle();

        this.blending = blending;
    }


}
