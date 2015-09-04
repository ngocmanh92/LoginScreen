package com.example.myapplication;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

import java.io.IOException;

public class VideoActivity extends ActionBarActivity implements
        View.OnClickListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, SeekBar.OnSeekBarChangeListener {

    public static final String video_url =
            "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    private VideoView videoView;

    ProgressDialog progressDialog;

    Button startOrPauseButton;
    Button stop;
    SeekBar progress;
    private boolean isCompleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        videoView = (VideoView) findViewById(R.id.video_view);
        videoView.setVideoURI(Uri.parse(video_url));
        videoView.setOnPreparedListener(this);

        videoView.setMediaController(new MediaController(this));

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        startOrPauseButton = (Button) findViewById(R.id.button_start_or_pause);
        stop = (Button) findViewById(R.id.button_stop);
        progress = (SeekBar) findViewById(R.id.progress);
        progress.setOnSeekBarChangeListener(this);

        startOrPauseButton.setOnClickListener(this);
        stop.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.button_start_or_pause) {
            if (mediaPlayer == null || isCompleted) {
                videoView.setVideoURI(Uri.parse(video_url));
                videoView.start();
                progressDialog.show();
                updateStartButtonState();
            }
            else if (mediaPlayer.isPlaying()) mediaPlayer.pause();
            else mediaPlayer.start();
            updateStartButtonState();
        } else if (id == R.id.button_stop) {
            if(mediaPlayer == null) return;;
            mediaPlayer.stop();
            mediaPlayer = null;
            updateStartButtonState();
        }
    }

    public void updateStartButtonState() {
        if (mediaPlayer == null) startOrPauseButton.setText("Start");
        else if(isCompleted)
            startOrPauseButton.setText("Replay");
        else if (videoView.isPlaying()) startOrPauseButton.setText("Pause");
        else startOrPauseButton.setText("Resume");
    }

    MediaPlayer mediaPlayer;

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        mediaPlayer.start();
        progressDialog.dismiss();
        updateStartButtonState();
        progress.setMax(mediaPlayer.getDuration());
        progress.setProgress(mediaPlayer.getCurrentPosition());
        mediaPlayer.setOnCompletionListener(this);
        startTimer();
        isCompleted = false;
    }

    private void onTime(){
        if(mediaPlayer == null) return;
        progress.setProgress(mediaPlayer.getCurrentPosition());
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }


    private boolean isContinue;
    private final Handler handler = new Handler();
    private Runnable counterRunnable = new Runnable() {
        @Override
        public void run() {
            if(!isContinue) return;
            handler.postDelayed(this,1000);
            onTime();
        }
    };

    private void startTimer(){
        isContinue = true;
        handler.postDelayed(counterRunnable,1000);
    }

    private void stopTimer(){
        isContinue = false;
    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.i("", "onCompletion " + mediaPlayer.getCurrentPosition());
        isCompleted = true;
        stopTimer();
        updateStartButtonState();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if(mediaPlayer == null) return;
        mediaPlayer.seekTo(seekBar.getProgress());
    }
}
