package com.example.abercrombie.activity;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.abercrombie.R;

public class webview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        final ActionBar actionBar = getActionBar();

        if (actionBar != null) {

            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Retrieving the URL from the main activity intent and passing it to webview

        String URL = getIntent().getStringExtra("URL");
        WebView webView = findViewById(R.id.web);
        webView.loadUrl(URL);


    }


}

