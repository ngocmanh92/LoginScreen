package com.example.myapplication;

import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;

public class AnimationActivity extends ActionBarActivity implements View.OnClickListener {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        imageView = (ImageView) findViewById(R.id.imageview);
        imageView.setOnClickListener(this);

    }

    protected void animTesting(){
        AnimatorSet set = new AnimatorSet();
        set.playTogether(
            ObjectAnimator.ofFloat(imageView, "alpha", 0,1).setDuration(2000),
            ObjectAnimator.ofFloat(imageView, "translationY", 0,100).setDuration(2000),
            ObjectAnimator.ofFloat(imageView, "scaleX", 0,1).setDuration(2000),
            ObjectAnimator.ofFloat(imageView, "rotationX", 0,1).setDuration(2000)
        );
        set.start();
    }


    @Override
    public void onClick(View view) {
        animTesting();
    }
}
