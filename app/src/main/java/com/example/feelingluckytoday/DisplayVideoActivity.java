package com.example.feelingluckytoday;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.VideoView;

import java.util.Random;

public class DisplayVideoActivity extends AppCompatActivity {

    Random randomVideo = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_video);

        VideoView video = findViewById(R.id.videoView);

    }
}