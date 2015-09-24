package com.lemonlab.ssuapp.View;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lemonlab.ssuapp.R;

public class ReadingRoomActivity extends AppCompatActivity implements View.OnClickListener{

    Vibrator vibe;
    TextView textView;
    Intent intent;
    ImageButton imageButton;
    ImageButton backButton;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_room);

        vibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
        intent = getIntent();
        textView = (TextView)findViewById(R.id.readingTextView);
        imageButton = (ImageButton)findViewById(R.id.readingimageButton);
        backButton = (ImageButton)findViewById(R.id.backButton);
        webView = (WebView)findViewById(R.id.readingwebView);

        textView.setText(intent.getStringExtra("name"));

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);

        webView.setWebViewClient(new WebBrowserClient());
        webView.getSettings().setDefaultTextEncodingName("Euc-kr");
        webView.getSettings().setJavaScriptEnabled(true);

        webView.loadUrl(intent.getStringExtra("Url"));

        imageButton.setOnClickListener(this);
        backButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        vibe.vibrate(15);
        switch(v.getId()){
            case R.id.readingimageButton:
                webView.loadUrl(intent.getStringExtra("Url"));
                Toast.makeText(getApplicationContext(), "새로고침 되었습니다.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.backButton:
                finish();
                break;
        }
    }


    final class WebBrowserClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            Log.d("webview",url);
            return true;
        }
        @Override
        public void onLoadResource(WebView view, String url) {
            Log.d("webview", "webview remove js");
            view.loadUrl("javascript:var div  = document.getElementById('header'); div.parentNode.removeChild(div);");
            view.loadUrl("javascript:var div  = document.getElementById('bbs-prevnext'); div.parentNode.removeChild(div);");
            view.loadUrl("javascript:var div  = document.getElementById('footer'); div.parentNode.removeChild(div);");
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            Log.d("webview", "webview loaded");
        }
    }
}
