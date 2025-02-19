package newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class show_content_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_show_content);

        WebView webView=findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);




        Intent intent=getIntent();

        String url=intent.getStringExtra("url");

        if(url!=null)
        {
            webView.loadUrl(url);
        }





    }
}