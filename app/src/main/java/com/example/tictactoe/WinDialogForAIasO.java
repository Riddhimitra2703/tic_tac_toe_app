package com.example.tictactoe;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinDialogForAIasO extends Dialog {

    Button exit;
    private final String message;
    private final PlayWithAIAsO playWithAI;
    TextView messageTxt;
    Button startAgainBtn;

    public WinDialogForAIasO(Context context, String message, PlayWithAIAsO playWithAI) {
        super(context);
        this.message = message;
        this.playWithAI = playWithAI;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win_dialog_for_aias_x);

        messageTxt = findViewById(R.id.messageTxt);
        startAgainBtn = findViewById(R.id.btn_start_again);
        exit = findViewById(R.id.btn_exit);

        messageTxt.setText(message);

        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithAI.restartMatch();
                dismiss();
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playWithAI.finish();
            }
        });
    }
}