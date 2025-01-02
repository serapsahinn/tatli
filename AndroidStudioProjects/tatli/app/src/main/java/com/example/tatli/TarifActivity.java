package com.example.tatli;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class TarifActivity extends AppCompatActivity {
    private WebView videoWebView;
    private TextView nameTextView;
    private TextView descriptionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarif);


        videoWebView = findViewById(R.id.videoWebView);
        nameTextView = findViewById(R.id.tarifNameTextView);
        descriptionTextView = findViewById(R.id.tarifDescriptionTextView);


        videoWebView.getSettings().setJavaScriptEnabled(true);
        videoWebView.setWebChromeClient(new WebChromeClient());
        videoWebView.setWebViewClient(new WebViewClient());


        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");
        String videoUrl = getIntent().getStringExtra("videoUrl");
        String tarif=getIntent().getStringExtra("tarif");


        nameTextView.setText(name);
        descriptionTextView.setText(description);
       nameTextView.setText(tarif);

        if (videoUrl != null && !videoUrl.isEmpty()) {
            // YouTube embed HTML'i
            String videoHtml = "<html><body style='margin:0;padding:0;'>" +
                    "<iframe width='100%' height='100%' src='" + videoUrl + "' " +
                    "frameborder='0' allowfullscreen></iframe></body></html>";

            videoWebView.loadData(videoHtml, "text/html", "utf-8");
        }


    }
}