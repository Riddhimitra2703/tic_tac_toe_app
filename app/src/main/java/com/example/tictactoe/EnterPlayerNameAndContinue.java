package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EnterPlayerNameAndContinue extends AppCompatActivity {

    Button Continue;
    EditText playerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_player_name_and_continue);

        Continue = findViewById(R.id.btn_continue);
        playerName = findViewById(R.id.PlayerNames);


        Continue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final String getPlayerName = playerName.getText().toString();

                    if (!getPlayerName.isEmpty()) {
                        Intent intent = new Intent(EnterPlayerNameAndContinue.this, CreateJoinOnlineRoom.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(EnterPlayerNameAndContinue.this, "Please Enter player Name", Toast.LENGTH_SHORT).show();
                    }
                }
        });
    }
}