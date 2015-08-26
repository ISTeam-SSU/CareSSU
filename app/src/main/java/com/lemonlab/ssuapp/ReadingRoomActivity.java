package com.lemonlab.ssuapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;

public class ReadingRoomActivity extends AppCompatActivity {

    TextView textView;
    Intent intent;
    ImageButton imageButton;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_room);

        intent = getIntent();
        textView = (TextView)findViewById(R.id.readingTextView);
        imageButton = (ImageButton)findViewById(R.id.readingimageButton);
        webView = (WebView)findViewById(R.id.readingwebView);

        textView.setText(intent.getStringExtra("name"));

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setWebChromeClient(new WebBrowserClient());
        webView.getSettings().setDefaultTextEncodingName("Euc-kr");

        webView.loadUrl(intent.getStringExtra("Url"));

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl(intent.getStringExtra("Url"));
            }
        });
    }


    final class WebBrowserClient extends WebChromeClient {
        public boolean onJsAlert(WebView view, String url, String message, JsResult result){
            result.confirm();
            return  true;
        }
    }
}
