package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoosePlayerMode extends AppCompatActivity {

     Button vsAI, play_N_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_player_mode);

        vsAI = findViewById(R.id.btn_vsAI);
        play_N_pass = findViewById(R.id.btn_play_N_pass);

        vsAI.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePlayerMode.this,ChooseXorOForAI.class);
                startActivity(intent);
            }
        });

        play_N_pass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChoosePlayerMode.this,AddPlayers.class);
                startActivity(intent);
            }
        });
    }
}