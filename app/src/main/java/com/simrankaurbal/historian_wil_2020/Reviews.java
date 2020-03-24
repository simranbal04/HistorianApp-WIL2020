package com.simrankaurbal.historian_wil_2020;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class Reviews extends AppCompatActivity {

@Override
    protected void onCreate(Bundle savedInstanceState){
    super.onCreate(savedInstanceState);
    setContentView(R.layout.reviews);

    WebView webview = (WebView) findViewById(R.id.webview);
    webview.getSettings().setJavaScriptEnabled(true);
    webview.setWebViewClient(new WebViewClient());
    //webview.loadUrl("https://maps.google.com/maps/contrib/100178072576030881292\\\"\\u003eLuna Almond\\u003c/a\\u003e");
    webview.loadUrl("https://maps.google.com/maps/contrib/106874872047568270518\\\"\\u003eA Google User\\u003c/a\\u003e");

}
}
