package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    Button play, online, settings ;
    MediaPlayer mediaPlayer;
    ImageView exit, rate_us;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        mediaPlayer = MediaPlayer.create(HomePage.this,R.raw.app_bg_music);
        mediaPlayer.start();

        play = findViewById(R.id.btn_play);
        online = findViewById(R.id.btn_online);
        settings = findViewById(R.id.btn_settings);

        exit = findViewById(R.id.exit_btn);
        rate_us = findViewById(R.id.rate_us);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(HomePage.this, ChoosePlayerMode.class);
                    startActivity(intent);
                }
        });

        online.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(HomePage.this,EnterPlayerNameAndContinue.class);
                        startActivity(intent);
            }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(HomePage.this);
                dialog.setContentView(R.layout.custom_exit_dialog);

                Button exitbtn = dialog.findViewById(R.id.btn_exit);
                Button cancel = dialog.findViewById(R.id.btn_cancel);

                exitbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        BackgroundSoundService bgService = new BackgroundSoundService();
                        bgService.onDestroy();
                        dialog.dismiss();
                        finish();
                    }
                });

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        rate_us.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    public  void onBackPressed() {
        super.onBackPressed();
        BackgroundSoundService bgService = new BackgroundSoundService();
        bgService.onDestroy();
        finish();
    }
}