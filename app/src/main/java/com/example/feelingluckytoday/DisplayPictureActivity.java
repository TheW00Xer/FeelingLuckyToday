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
import java.util.Objects;
import java.util.Random;

public class DisplayPictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_picture);

        boolean fileFound = false;
        Random randomNumber = new Random();
        //Generating random number
        ImageView image = findViewById(R.id.imageView);
        //Declaring View called image, finding it by it's Id
        //final String[] okFileExtension = {".jpg",".png"};
        //Declaring value of file name extensions we want the app to use
        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //File picturesDirectory = new File(Environment.getExternalStorageDirectory().toString() + "/Pictures/");
        //Declaring directory where app obtains picture files
        try {
            if (picturesDirectory.isDirectory()) {
                String[] paths = picturesDirectory.list();
                assert paths != null;
                for (String path : paths) {
                    //if (path.toLowerCase().endsWith(Arrays.toString(okFileExtension))) {
                    if (path.toLowerCase().endsWith(".jpg")) {
                        System.out.println(path);
                        fileFound = true;
                    } else if (!fileFound) {
                        Toast.makeText(getApplicationContext(), "No files with .jpg or .png extension in 'Pictures' directory.", Toast.LENGTH_SHORT).show();
                        System.out.println("No files with .jpg or .png extension in 'Pictures' directory.");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (fileFound) {
            File[] listFiles = picturesDirectory.listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return !file.isHidden();
                }
            });
            //Creating list of files from picturesDirectory that will match our Filename Filter conditions
            File randomPicture = Objects.requireNonNull(listFiles)[randomNumber.nextInt(listFiles.length)];
            Uri pictureUri = Uri.fromFile(randomPicture);
            //Getting pat to selected random picture
            image.setImageURI(Uri.parse(String.valueOf(pictureUri)));
            //Sets path for picture to be displayed in imageView
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