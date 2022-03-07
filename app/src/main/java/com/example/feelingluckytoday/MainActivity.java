package com.example.feelingluckytoday;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

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
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}