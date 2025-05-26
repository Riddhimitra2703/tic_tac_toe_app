package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateJoinOnlineRoom extends AppCompatActivity {

    Button create, join;
    EditText roomCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_join_online_room);

        create = findViewById(R.id.btn_create);
        join = findViewById(R.id.btn_join);

        roomCode = findViewById(R.id.roomCode);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateJoinOnlineRoom.this, PlayOnline.class);
                startActivity(intent);
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateJoinOnlineRoom.this, PlayOnline.class);
                startActivity(intent);
            }
        });
    }
}