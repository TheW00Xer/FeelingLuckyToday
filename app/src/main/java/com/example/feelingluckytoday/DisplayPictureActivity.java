package com.example.feelingluckytoday;

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
        //declaring directory where app obtains picture files
        File[] listFiles = picturesDirectory.listFiles();
        //getting list of all files in picture directory
        Random randomNumber = new Random();
        //generating random number
        File randomPicture = null;
        //declaring file "randomPicture" that is required to be NonNull object
        if (listFiles != null) {
            randomPicture = listFiles[randomNumber.nextInt(listFiles.length)];
            //using randomNumber to select a video from previously obtained list of files that don't have nul value
        }
        Uri pictureUri = Uri.fromFile(randomPicture);
        //getting pat to selected random picture

        ImageView image = findViewById(R.id.imageView);
        //Declaring View called image, finding it by it's Id
        image.setImageURI(pictureUri);
        //sets path for picture to be displayed in ImageView

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