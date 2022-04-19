package com.example.feelingluckytoday;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;

public class TestButtonActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_button);

        ImageView image = findViewById(R.id.imageView2);
        //VideoView video = findViewById(R.id.videoView2);
        Random randomNumber = new Random();
        final boolean[] fileFound = {false};

        File picturesDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        File[] fileList = picturesDirectory.listFiles();
        if (fileList != null)
            for (File file : fileList) {
                try {
                    Files.walk(Paths.get(String.valueOf(picturesDirectory)))
                            .filter(Files::isRegularFile)
                            .forEach(filePath -> {
                                String name = filePath.getFileName().toString();
                                if (name.endsWith(".jpg")) {
                                    fileFound[0] = true;
                                    //System.out.println(filePath.getFileName());

                                } else if (name.endsWith(".png")) {
                                    fileFound[0] = true;
                                    //System.out.println(filePath.getFileName());
                                }
                                //File randomPicture = new File(fileList()[randomNumber.nextInt(fileList().length + 1) + fileList().length]);
                                //int i = randomNumber.nextInt((fileList().length));
                                //System.out.println(i);
                                //r.nextInt(b - a + 1) + a
                                //Uri pictureUri = Uri.fromFile(randomPicture);
                                //image.setImageURI(pictureUri);
                            });

                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (fileFound[0]) {
                    File[] listFiles = picturesDirectory.listFiles(new FileFilter() {
                        @Override
                        public boolean accept(File file) {
                            return !file.isHidden();
                        }
                    });
                    System.out.println(listFiles.length);
                    //Creating list of files from picturesDirectory that will match our Filename Filter conditions
                    //File randomPicture = Objects.requireNonNull(listFiles)[randomNumber.nextInt(listFiles.length)];
                    //Uri pictureUri = Uri.fromFile(randomPicture);
                    //Getting pat to selected random picture
                    //image.setImageURI(Uri.parse(String.valueOf(pictureUri)));
                    //Sets path for picture to be displayed in imageView
                }
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