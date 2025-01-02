package com.example.tatli;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoadingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loading);

        ImageView img=findViewById(R.id.img);

        Animation a1 = AnimationUtils.loadAnimation(LoadingActivity.this,R.anim.animasyon);
        img.startAnimation(a1);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoadingActivity.this,MainActivity.class);
            startActivity(intent);
        }, 6000);



    }
}