package com.example.feelingluckytoday;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.Random;

public class DisplayVideoActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        boolean fileFound = false;
        Random randomNumber = new Random();
        //Generating random number
        VideoView video = findViewById(R.id.videoView);
        //Declaring View called video, finding it by it's Id
        MediaController mediaController = new MediaController(this);
        //Declaring MediaController element
        File videosDirectory = null;
        //Declaring final directory that will be assigned a path from either main or secondary directory after passing filters
        File videosDirectory1 = new File(Environment.getExternalStorageDirectory().toString() + "/Lucky/");
        //Declaring primary directory where app obtains video files
        File videosDirectory2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        //Declaring secondary directory where app obtains video files if primary is not available
        try {
            if (videosDirectory1.isDirectory()) {
                String[] paths = videosDirectory1.list();
                assert paths != null;
                for (String path : paths) {
                    if (path.toLowerCase().endsWith(".mp4")) {
                        System.out.println(path);
                        fileFound = true;
                        videosDirectory = videosDirectory1;
                    } else if (!fileFound) {
                        Toast.makeText(getApplicationContext(),"No files with .mp4 extension in 'Lucky' directory.", Toast.LENGTH_SHORT).show();
                        System.out.println("No files with .mp4 extension in 'Lucky' directory");
                        videosDirectory = videosDirectory2;
                    }
                }
            } else if (videosDirectory2.isDirectory()) {
                //assert videosDirectory != null;
                String[] paths = videosDirectory2.list();
                assert paths != null;
                for (String path : paths) {
                    if (path.toLowerCase().endsWith(".mp4")) {
                        System.out.println(path);
                        fileFound = true;
                        videosDirectory = videosDirectory2;
                    }
                }
                if (!fileFound) {
                    Toast.makeText(getApplicationContext(), "No files with .mp4 extension in 'Movies' directory.", Toast.LENGTH_SHORT).show();
                    System.out.println("No files with .mp4 extension in 'Movies' directory");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (fileFound) {
                File[] listFiles = videosDirectory.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File file) {
                        return !file.isHidden();
                    }
                });
                //Creating list of files from videosDirectory that will match our Filename Filter conditions
                File randomVideo = Objects.requireNonNull(listFiles)[randomNumber.nextInt(listFiles.length)];
                Uri videoUri = Uri.fromFile(randomVideo);
                //Getting pat to selected random video
                video.setVideoURI(Uri.parse(String.valueOf(videoUri)));
                //Sets path for video to be displayed in videoView
            }
        }   catch (Exception e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        //Calling the action bar
        if (actionBar != null) { //Showing the back button in action bar
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaController.setAnchorView(video);
                //Attaches mediaController to videoView
                video.setMediaController(mediaController);
                //Allows mediaController to control videoView (play, pause,...)
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

    @Override
    public void onConfigurationChanged(@NotNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            findViewById(R.id.videoView).setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            findViewById(R.id.videoView).setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }

}