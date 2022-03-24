package com.example.feelingluckytoday;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.Random;

public class DisplayPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File[] listFiles = picturesDirectory.listFiles();
        Random r = new Random();
        File randomPicture = null;
        if (listFiles != null) {
            randomPicture = listFiles[r.nextInt(listFiles.length)];
        }
        Uri pictureUri = Uri.fromFile(randomPicture);

        //int[] images = {R.drawable.doge, R.drawable.monke, R.drawable.guine};
        //Random randomNumber = new Random(System.currentTimeMillis());

        ImageView image = findViewById(R.id.imageView); //Declaring View called image, finding it by it's Id
        //image.setImageResource(images[randomNumber.nextInt(images.length)]); //sets resource for

        image.setImageURI(pictureUri);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
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