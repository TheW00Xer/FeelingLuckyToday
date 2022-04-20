package com.example.feelingluckytoday;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileFilter;
import java.util.Random;

public class DisplayPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        Random randomNumber = new Random();
        //Generating random number
        ImageView image = findViewById(R.id.imageView);
        //Declaring View called image, finding it by it's Id
        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //Declaring directory where app obtains picture files

        if (picturesDirectory.exists()) {
            File[] listFiles = picturesDirectory.listFiles(new FileFilter() {
                //Creating list of files from picturesDirectory that will match our File filter conditions
                @Override
                public boolean accept(File file) {
                    if (file.isHidden()) {
                        return false;
                    }
                    if (file.isDirectory()) {
                        return false;
                    }
                    return file.getPath().endsWith(".jpg") || file.getPath().endsWith(".png");
                }
            });
            if (listFiles.length>0) {
                //Checking if length of list files is bigger than 0 (zero)
                File randomPicture = (listFiles)[randomNumber.nextInt(listFiles.length)];
                //Picking random file from list of files
                Uri pictureUri = Uri.fromFile(randomPicture);
                //Getting path to selected random picture
                image.setImageURI(Uri.parse(String.valueOf(pictureUri)));
                //Sets path for picture to be displayed in imageView
            } else {
                Toast.makeText(getApplicationContext(), "No files with .jpg or .png extension in 'Pictures' directory.", Toast.LENGTH_SHORT).show();
                System.out.println("No files with .jpg or .png extension in 'Pictures' directory.");
            }
        } else {
            Toast.makeText(getApplicationContext(), "There is no 'Pictures' directory on this device.", Toast.LENGTH_SHORT).show();
        }

        ActionBar actionBar = getSupportActionBar();
        //Calling the action bar
        if (actionBar != null) { //Showing the back button in action bar
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