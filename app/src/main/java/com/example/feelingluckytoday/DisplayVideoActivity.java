package com.example.feelingluckytoday;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DisplayVideoActivity extends AppCompatActivity {

    int[] videos = {R.raw.surprised_dude, R.raw.busy_monke, R.raw.eating_guine};
    Random randomUri = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);

        MediaController mediaController = new MediaController(this);
        VideoView video = findViewById(R.id.videoView);
        String uriPath = "android.resource://".concat(getPackageName()).concat("/raw/").concat(String.valueOf(videos[randomUri.nextInt(videos.length)]));
        video.setVideoURI(Uri.parse(uriPath));

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                //attaches mediaController to videoView
                mediaController.setAnchorView(video);
                //allows mediaController to control videoView (play, pause,...)
                video.setMediaController(mediaController);
                video.start();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}