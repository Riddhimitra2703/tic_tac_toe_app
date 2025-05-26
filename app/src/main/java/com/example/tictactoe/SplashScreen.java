package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 3000;

    Animation topAnim, bottomAnim;
    ImageView image1, image2;
    TextView logo;
    TextView version;
    TextView designer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image1 = findViewById(R.id.appIcon);
        image2 = findViewById(R.id.designerLogo);
        logo = findViewById(R.id.appName);
        version = findViewById(R.id.version);
        designer = findViewById(R.id.designer);


        image1.setAnimation(topAnim);
        logo.setAnimation(topAnim);
        version.setAnimation(topAnim);
        image2.setAnimation(bottomAnim);
        designer.setAnimation(bottomAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);
    }
}