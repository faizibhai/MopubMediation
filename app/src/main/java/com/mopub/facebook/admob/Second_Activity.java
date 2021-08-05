package com.mopub.facebook.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Second_Activity extends AppCompatActivity {


    RelativeLayout view;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        view = findViewById(R.id.mainlayout);
        linearLayout = (LinearLayout)findViewById(R.id.layad);


        Ad_Helper.showNativeAd(this, view);
        Ad_Helper.showBannerAd(this,linearLayout);



    }

    @Override
    protected void onResume() {
        super.onResume();
        Ad_Helper.showBannerAd(this,linearLayout);

    }
}