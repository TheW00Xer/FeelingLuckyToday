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

        ImageButton showImage = findViewById(R.id.imageButton); //Defines Image button and finds it by it's View Id
        showImage.setOnClickListener(new View.OnClickListener() { //Set on click listener for this button
            @Override
            public void onClick(View view) { //when user clicks button it will call function "openPicture"
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

    /**
     * Function that starts new activity which will open separate new window
     * We created intent for this activity that contains information what we want to do ie. start Activity called DisplayPictureActivity
     */
    private void openPicture(){
        Intent intent = new Intent(this, DisplayPictureActivity.class);
        startActivity(intent);
    }
    private void openVideo(){
        Intent intent = new Intent(this, DisplayVideoActivity.class);
        startActivity(intent);
    }
    private void openSearch(){
        Intent intent = new Intent(this, DisplayWebPageActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //Event that creates return button, in this case a button that will close the app since we are on first Main page and there is nowhere to return
        if (item.getItemId() == android.R.id.home) { //If user clicks on button
            this.finish(); //this activity will end, app will close
            return true; //Returns value true, closes app
        }
        return super.onOptionsItemSelected(item);
    }
}