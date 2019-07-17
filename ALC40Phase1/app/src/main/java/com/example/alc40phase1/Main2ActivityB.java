package com.example.alc40phase1;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Main2ActivityB extends AppCompatActivity {

    private WebView ALC4webView;
    private ProgressBar progressBar;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_b);
        getSupportActionBar().setTitle("About ALC");

        ALC4webView = findViewById(R.id.id_ALC_webview);
        progressBar = findViewById(R.id.id_progressBar);
        imageView = findViewById(R.id.id_imageView);

        progressBar.setMax(100);

        ALC4webView.getSettings().setJavaScriptEnabled(true);
        ALC4webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                   handler.proceed();
               }
        });
        ALC4webView.loadUrl("https://andela.com/alc/");

        ALC4webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                getSupportActionBar().setTitle(title);
            }

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                imageView.setImageBitmap(icon);
            }
        });
    }

    public void onBackPressed(){
        if(ALC4webView.canGoBack()){
            ALC4webView.goBack();
        }
        else{
            finish();
        }
    }
}
