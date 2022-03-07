package com.example.feelingluckytoday;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class DisplayWebPageActivity extends AppCompatActivity {

    public static final String [] searchEngines = {
            "https://www.google.com/",
            "https://www.bing.com/",
            "https://duckduckgo.com/"
    };

    Random url = new Random();
    String randomUrl = searchEngines[url.nextInt(searchEngines.length)];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_web_page);

        WebView browser = findViewById(R.id.searchPage);
        browser.loadUrl(String.valueOf(randomUrl));

    }
}