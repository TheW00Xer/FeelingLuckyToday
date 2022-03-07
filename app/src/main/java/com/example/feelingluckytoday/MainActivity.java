package com.example.feelingluckytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton showImage = findViewById(R.id.imageButton);
        showImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPicture();
            }
        });

        ImageButton showVideo = findViewById(R.id.videoButton);
        showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openVideo();
            }
        });

        ImageButton showSearchEngine = findViewById(R.id.searchButton);
        showSearchEngine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSearch();
            }
        });

    }
    public void openPicture(){
        Intent intent = new Intent(this, DisplayPictureActivity.class);
        startActivity(intent);
    }
    public void openVideo(){
        Intent intent = new Intent(this, DisplayVideoActivity.class);
        startActivity(intent);
    }
    public void openSearch(){
        Intent intent = new Intent(this, DisplayWebPageActivity.class);
        startActivity(intent);
    }
}