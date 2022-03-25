package com.example.feelingluckytoday;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Objects;
import java.util.Random;

public class DisplayVideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        File videosDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        //declaring directory where app obtains video files

        File[] listFiles = videosDirectory.listFiles(new FilenameFilter() { //Creating list of files from videosDirectory that will match our Filename filter conditions
            public boolean accept(File picturesDirectory, String name) {
                return name.endsWith(".mp4"); //Returns only files ending with .mp4
            }
        });

        //File[] listFiles = videosDirectory.listFiles();
        //getting list of all files in video directory

        Random randomNumber = new Random();
        //generating random number
        File randomVideo = Objects.requireNonNull(listFiles)[randomNumber.nextInt(listFiles.length)];
        //declaring file "randomVideo" that is required to be NonNull object and then using randomNumber to select a video from previously obtained list of files
        Uri videoUri = Uri.fromFile(randomVideo);
        //getting path to selected random video

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        MediaController mediaController = new MediaController(this);
        VideoView video = findViewById(R.id.videoView);
        video.setVideoURI(Uri.parse(String.valueOf(videoUri)));
        //setting videoView path for video file

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