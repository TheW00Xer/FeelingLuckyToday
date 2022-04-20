package com.example.feelingluckytoday;

import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.Random;

public class DisplayVideoActivity extends AppCompatActivity {
    //Following two files need to be declared here in order to be accessible by all functions
    File videosDirectory;
    //Declaring final directory that will be assigned a path from either main or secondary directory after passing filters
    File[] listFiles = new File[0];
    //Declaring new empty list of files.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        MediaController mediaController = new MediaController(this);
        //Declaring MediaController element
        File videosDirectory1 = new File(Environment.getExternalStorageDirectory().toString() + "/Lucky/");
        //Declaring primary directory where app obtains video files
        File videosDirectory2 = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
        //Declaring secondary directory where app obtains video files if primary is not available
        VideoView video = findViewById(R.id.videoView);
        //Declaring View called video, finding it by it's Id

        try {
            if (videosDirectory1.isDirectory()) {
                if (!videosDirectory1.isDirectory()) {
                    Toast.makeText(getApplicationContext(), "'Lucky' directory doesn't exist on this device.", Toast.LENGTH_SHORT).show();
                    System.out.println("'Lucky' directory doesn't exist on this device.");
                }
                videosDirectory = videosDirectory1;
                fileChecker();
                //Calling function to check files with defined filter
                if (listFiles.length > 0) {
                    //Checking if length of list files is bigger than 0 (zero)
                    videoPicker();
                    //Calling function to pick random video from list of files
                } else {
                    Toast.makeText(getApplicationContext(), "No files with .mp4 extension in 'Lucky' directory.", Toast.LENGTH_SHORT).show();
                    System.out.println("No files with .mp4 extension in 'Lucky' directory.");
                }
            } if (!videosDirectory1.isDirectory() || listFiles.length <= 0) {
                if (videosDirectory2.isDirectory()) {
                    videosDirectory = videosDirectory2;
                    fileChecker();
                    if (listFiles.length > 0) {
                        videoPicker();
                    } else {
                        Toast.makeText(getApplicationContext(), "No files with .mp4 extension in 'Movies' directory.", Toast.LENGTH_SHORT).show();
                        System.out.println("No files with .mp4 extension in 'Movies' directory.");
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "'Movies' directory doesn't exist on this device.", Toast.LENGTH_SHORT).show();
                    System.out.println("'Movies' directory doesn't exist on this device.");
                }
            }
        } catch (Exception e) {
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

    public void fileChecker() {
        //Function for checking if files meet set conditions
        listFiles = videosDirectory.listFiles(new FileFilter() {
            //Creating list of files from videosDirectory1 that will match our File filter conditions
            @Override
            public boolean accept(File file) {
                if (file.isHidden()) {
                    return false;
                }
                if (file.isDirectory()) {
                    return false;
                }
                return file.getPath().endsWith(".mp4");
            }
        });
    }

    public void videoPicker() {
        //Function for picking random video from checked list of files
        Random randomNumber = new Random();
        //Generating random number
        VideoView video = findViewById(R.id.videoView);
        //Declaring View called video, finding it by it's Id
        File randomVideo = Objects.requireNonNull(listFiles)[randomNumber.nextInt(listFiles.length)];
        //Picking random file from list of files
        Uri videoUri = Uri.fromFile(randomVideo);
        //Getting pat to selected random video
        video.setVideoURI(Uri.parse(String.valueOf(videoUri)));
        //Sets path for video to be displayed in videoView
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