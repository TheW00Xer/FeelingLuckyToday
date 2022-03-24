package com.example.feelingluckytoday;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    private final int STORAGE_PERMISSION_CODE = 1; // Declaration of storage permission identifier

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // Showing the back button in action bar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        ImageButton showImage = findViewById(R.id.imageButton); // Defines Image button and finds it by it's View Id
        showImage.setOnClickListener(new View.OnClickListener() { // Set on click listener for this button
            @Override
            public void onClick(View view) { // When user clicks button first the app will check if the permission to access external storage was granted
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openPicture(); // If the app has permission function "openPicture" is called
                } else {
                    requestStoragePermission(); // If the storage access permission is not granted the function "requestStoragePermission" is called
                }
            }
        });

        ImageButton showVideo = findViewById(R.id.videoButton);
        showVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    openVideo();
                } else {
                    requestStoragePermission();
                }
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
     * Function that shows dialog window asking user for "Storage access permission"
     */
    private void requestStoragePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            new AlertDialog.Builder(this) // New dialog window is designed and described here
                    .setTitle("Storage access permission needed!") // Text in dialog window shows explanation message
                    .setMessage("This permission is needed to access files in your gallery.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() { // Sets "OK" button in the dialog window for user to accept
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE); // Android system permission dialog window is called
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() { // Sets "Cancel" button in the dialog window for user to deny
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss(); // Dialog window is closed
                        }
                    })
                    .create().show(); // Creates and shows this new dialog window on screen to user
        } else {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }

    /**
     * This function compares if the app has permission to access the external storage or no
     * @param requestCode is holding value representing if permission is granted or no
     * @param permissions type of permission
     * @param grantResults gives app permission to access the external storage
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
            }
        }
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
