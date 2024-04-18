package com.example.quiz_game;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    private static final int SPLASH_DURATION = 2000;
    private ImageView logoImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        logoImageView = findViewById(R.id.logo);
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(logoImageView, "scaleX", 1.0f, 5.0f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logoImageView, "scaleY", 1.0f, 5.0f);
        scaleX.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleY.setInterpolator(new AccelerateDecelerateInterpolator());

        scaleX.setDuration(SPLASH_DURATION);
        scaleY.setDuration(SPLASH_DURATION);

        scaleX.start();
        scaleY.start();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logoImageView.animate().alpha(0.0f).setDuration(500);
            }
        }, SPLASH_DURATION);

        // Start the next activity after the splash duration
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, HomeScreen.class));
                finish();
            }
        }, SPLASH_DURATION + 500);
    }
}