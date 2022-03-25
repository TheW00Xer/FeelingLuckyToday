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
import java.io.FilenameFilter;
import java.util.Random;

public class DisplayPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //Declaring directory where app obtains picture files

        final String[] okFileExtension = new String[]{"jpg","png"};
        //Declaring value of file name extensions we want the app to use

        File[] listFiles = picturesDirectory.listFiles(new FilenameFilter() { //Creating list of files from picturesDirectory that will match our Filename Filter conditions
            public boolean accept(File picturesDirectory, String name) { //Function that compares filenames to our Filter conditions
                for (String extension : okFileExtension) { //String value of file extensions will be compared with extensions we declared above in okFileExtension
                    if (name.endsWith(extension))
                        return true; //Returns only files ending with .jpg or .png
                }
                return false;
            }
        });
        /*
          App can run even without this filter but if user for example has multiple folders within the Picture
          folder, App displays blank/black screen when it attempts to get the folder.
        */

        Random randomNumber = new Random();
        //generating random number
        File randomPicture = null;
        //declaring file "randomPicture"
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