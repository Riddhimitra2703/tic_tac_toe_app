package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

public class ChooseXorOForAI extends AppCompatActivity {

    Switch userAsX, userAsO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_xor_ofor_ai);

        userAsX = findViewById(R.id.userAsX);
        userAsO = findViewById(R.id.userAsO);

        userAsX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseXorOForAI.this, PlayWithAIAsX.class);
                startActivity(intent);
                finish();
            }
        });

        userAsO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseXorOForAI.this, PlayWithAIAsO.class);
                startActivity(intent);
                finish();
            }
        });
    }
}