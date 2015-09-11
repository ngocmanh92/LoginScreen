package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import com.example.myapplication.views.ImageProcessingView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageProcessingActivity extends ActionBarActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    boolean isDrawing;
    ImageProcessingView ipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_processing);
        CheckBox drawingCheckbox = (CheckBox) findViewById(R.id.checkbox_drawing);
        isDrawing = drawingCheckbox.isChecked();
        drawingCheckbox.setOnCheckedChangeListener(this);
        findViewById(R.id.button_clear).setOnClickListener(this);
        findViewById(R.id.button_blending).setOnClickListener(this);

        ipView = new ImageProcessingView(this);
        ipView.setWillNotDraw(false);
        ViewGroup group = (ViewGroup) findViewById(R.id.layout_image_processing_container);
        group.addView(ipView);
        initSpinner();

    }

    protected void initSpinner(){
        Spinner spinnerEffect = (Spinner) findViewById(R.id.spinner_blending_option);
        spinnerEffect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if(isDrawing) return;
                ArrayAdapter<PorterDuff.Mode> adapter =
                        (ArrayAdapter<PorterDuff.Mode>) adapterView.getAdapter();
                PorterDuff.Mode mode = adapter.getItem(position);
                try {
                    blend(mode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        List<PorterDuff.Mode> modes = new ArrayList<PorterDuff.Mode>();
        modes.add(PorterDuff.Mode.ADD);
        modes.add(PorterDuff.Mode.CLEAR);
        modes.add(PorterDuff.Mode.DARKEN);
        modes.add(PorterDuff.Mode.DST);
        modes.add(PorterDuff.Mode.DST_ATOP);
        modes.add(PorterDuff.Mode.DST_IN);
        modes.add(PorterDuff.Mode.DST_OUT);
        modes.add(PorterDuff.Mode.DST_OVER);
        modes.add(PorterDuff.Mode.LIGHTEN);
        modes.add(PorterDuff.Mode.MULTIPLY);
        modes.add(PorterDuff.Mode.OVERLAY);
        modes.add(PorterDuff.Mode.SCREEN);
        modes.add(PorterDuff.Mode.DST_OUT);
        modes.add(PorterDuff.Mode.DARKEN);

        ArrayAdapter<PorterDuff.Mode> adapter =
                new ArrayAdapter<PorterDuff.Mode>(
                        this,
                        android.R.layout.simple_spinner_dropdown_item,
                        modes);
        spinnerEffect.setAdapter(adapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        isDrawing = checked;
        ipView.setIsDrawing(isDrawing);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_clear)
            clear();
        else if (view.getId() == R.id.button_blending) {
            try {
                blend();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void clear() {
        ipView.clean();
    }


    public void blend()  {
        try {
            blend(PorterDuff.Mode.MULTIPLY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void blend(PorterDuff.Mode mode) throws Exception {
        Bitmap src = BitmapFactory.decodeStream(getAssets().open("effect.jpg"));
        Bitmap dist = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Bitmap result = blending(src, dist, mode);
        ipView.setBlendingBitmap(result);
        ipView.invalidate();
    }

    public Bitmap blending(Bitmap src, Bitmap dist, PorterDuff.Mode mode) {
        Bitmap result = Bitmap.createBitmap(src.getWidth(),
                src.getHeight(), Bitmap.Config.ARGB_4444
        );
        Canvas canvas = new Canvas(result);
        canvas.drawBitmap(src, 0, 0, new Paint());

        Bitmap recycle = dist;
        dist = Bitmap.createScaledBitmap(dist, src.getWidth(), src.getHeight(), false);
        recycle.recycle();

        Paint effect = new Paint();
        effect.setXfermode(new PorterDuffXfermode(mode));
        canvas.drawBitmap(dist, 0, 0, effect);

        src.recycle();
        dist.recycle();

        return result;
    }


}
