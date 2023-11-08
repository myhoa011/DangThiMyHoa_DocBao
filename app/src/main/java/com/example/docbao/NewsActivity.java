package com.example.docbao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toolbar;

public class NewsActivity extends AppCompatActivity {
    WebView wvBao;
    ImageView back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        wvBao = findViewById(R.id.wvBao);

        Intent intent = getIntent();

        String link = intent.getStringExtra("linkBao");

        wvBao.loadUrl(link);

        wvBao.setWebViewClient(new WebViewClient());
    }
}