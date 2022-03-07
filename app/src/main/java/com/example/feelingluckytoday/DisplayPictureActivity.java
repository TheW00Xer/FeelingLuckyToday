package com.example.feelingluckytoday;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DisplayPictureActivity extends AppCompatActivity {

    int[] images = {R.drawable.doge, R.drawable.monke, R.drawable.guine};
    Random randomImage = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        ImageView image = findViewById(R.id.imageView);
        image.setImageResource(images[randomImage.nextInt(images.length)]);
    }
}