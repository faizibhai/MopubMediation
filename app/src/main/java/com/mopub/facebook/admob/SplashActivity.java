package com.mopub.facebook.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    static int SPLASH_DELAY = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        loadMopUbAdsHere();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                menuIntent();
            }
        }, SPLASH_DELAY);
    }

    void loadMopUbAdsHere(){
        Ad_Helper.loadNativeAd(this);
        Ad_Helper.loadIntersitialAd(this);

    }

    private void menuIntent(){
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}