package com.mopub.facebook.admob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout = (LinearLayout)findViewById(R.id.layad);
        showMopUbMediaton(); // load and show mopub mediation ads on create





    }

    public void show_interstetial(View view) {
        startActivity(new Intent(MainActivity.this,Second_Activity.class));
        Ad_Helper.showIntersitial(MainActivity.this);
    }
    void showMopUbMediaton(){
        Ad_Helper.LoadMediationBannerAd(this,linearLayout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Ad_Helper.showBannerAd(this,linearLayout);

    }
}