package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class PlayWithAIAsO extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();
    private final Stack<Integer> storeBoxNo = new Stack<>();
    private final Stack<ImageView> storeImageId = new Stack<>();
    private Button Undo;
    private int turn = 1; //  0=finishGame, 1=AI'sTurn, 2=Human'sTurn
    private int totalSelectedBoxes = 1;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    ImageView back;
    int[] gameState = {3,3,3,3,3,3,3,3,3}; // 3=empty, 1=AI'sTurn, 2=Human'sTurn
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_ai_as_o);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);


        combinationList.add(new int[] {0,1,2});
        combinationList.add(new int[] {3,4,5});
        combinationList.add(new int[] {6,7,8});
        combinationList.add(new int[] {0,3,6});
        combinationList.add(new int[] {1,4,7});
        combinationList.add(new int[] {2,5,8});
        combinationList.add(new int[] {2,4,6});
        combinationList.add(new int[] {0,4,8});


        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        back = findViewById(R.id.img_back);
        Undo = findViewById(R.id.undo);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });

        int[] values = firstMove();
        turn = values[0];
        totalSelectedBoxes = values[1];

        Undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlayWithAIAsO.this, "You can't undo AI's first move", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void Click(View view) {
        ImageView img = (ImageView) view;
        int tag1 = Integer.parseInt(view.getTag().toString());

        if(gameState[tag1] == 3 && turn == 2) {
            img.setImageResource(R.drawable.oimage);
            gameState[tag1] = 2;

            if (checkResults()) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Won the Match!", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 1;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
                storeBoxNo.push(tag1);
                storeImageId.push(img);
            }
        }

        if(turn == 1) {
            turnAI();
        }

        Undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] values = undo();
                turn = values[0];
                totalSelectedBoxes = values[1];
            }
        });
    }

    public void turnAI() {
        // AI checks for its winning condition ------>

        //Check Rows -->

        if (gameState[0] == gameState[1] && gameState[1] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[2] = 1;
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //1
        } else if (gameState[1] == gameState[2] && gameState[2] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[0] = 1;
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //2
        } else if (gameState[0] == gameState[2] && gameState[2] == 1 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[1] = 1; //3
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //checks 1st row
        }


        else if (gameState[3] == gameState[4] && gameState[4] == 1 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[5] = 1; //4
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);resultDialog.show();
            return;
            //2
        } else if (gameState[4] == gameState[5] && gameState[5] == 1 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[3] = 1; //5
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //2
        } else if (gameState[3] == gameState[5] && gameState[5] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[4] = 1; //6
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //2
        } // checks 2nd row


        else if (gameState[6] == gameState[7] && gameState[7] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[8] = 1; //7
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
            //2
        } else if (gameState[7] == gameState[8] && gameState[8] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[6] = 1; //8
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[6] == gameState[8] && gameState[8] == 1 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[7] = 1; //9
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        }// checks 3rd row


        // Check Columns -->

        else if (gameState[0] == gameState[3] && gameState[3] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[6] = 1; //10
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[3] == gameState[6] && gameState[6] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[0] = 1; //11
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[0] == gameState[6] && gameState[6] == 1 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[3] = 1; //12
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } // checks 1st column


        else if (gameState[1] == gameState[4] && gameState[4] == 1 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[7] = 1; //13
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[4] == gameState[7] && gameState[7] == 1 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[1] = 1; //14
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[1] == gameState[7] && gameState[7] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[4] = 1; //15
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } // checks 2nd column


        else if (gameState[2] == gameState[5] && gameState[5] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[8] = 1; //16
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[5] == gameState[8] && gameState[8] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[2] = 1; //17
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[2] == gameState[8] && gameState[8] == 1 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[5] = 1; //18
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } // checks 3rd column


        //Checks Diagonals -->

        else if (gameState[0] == gameState[4] && gameState[4] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[8] = 1; //19
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[4] == gameState[8] && gameState[8] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[0] = 1; //20
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[0] == gameState[8] && gameState[8] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[4] = 1; //21
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } // checks Index (0,4,8) diagonal


        else if (gameState[2] == gameState[4] && gameState[4] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[6] = 1; //22
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[4] == gameState[6] && gameState[6] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[2] = 1; //23
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } else if (gameState[2] == gameState[6] && gameState[6] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            turn = 0;
            gameState[4] = 1; //24
            WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "You Lost The Match!", PlayWithAIAsO.this);
            resultDialog.setCancelable(false);
            resultDialog.show();
            return;
        } // checks Index (2,4,6) diagonal


        //AI checks for the winning conditions of the human ------>

        //Check Rows -->

        if (gameState[0] == gameState[1] && gameState[1] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 1; //25

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[1] == gameState[2] && gameState[2] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 1; //26

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[0] == gameState[2] && gameState[2] == 2 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.ximage);
            storeBoxNo.push(1);
            storeImageId.push(image2);
            gameState[1] = 2; //27

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } //checks 1st row


        else if (gameState[3] == gameState[4] && gameState[4] == 2 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.ximage);
            storeBoxNo.push(5);
            storeImageId.push(image6);
            gameState[5] = 1; //28

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[4] == gameState[5] && gameState[5] == 2 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.ximage);
            storeBoxNo.push(3);
            storeImageId.push(image4);
            gameState[3] = 1; //29

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[3] == gameState[5] && gameState[5] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 1; //30

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks 2nd row


        else if (gameState[6] == gameState[7] && gameState[7] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 1; //31

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[7] == gameState[8] && gameState[8] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 1; //32

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[6] == gameState[8] && gameState[8] == 2 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.ximage);
            storeBoxNo.push(7);
            storeImageId.push(image8);
            gameState[7] = 1; //33

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks 3rd row


        // Check Columns -->

        else if (gameState[0] == gameState[3] && gameState[3] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 1; //34

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }

        } else if (gameState[3] == gameState[6] && gameState[6] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 1; //35

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[0] == gameState[6] && gameState[6] == 2 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.ximage);
            storeBoxNo.push(3);
            storeImageId.push(image4);
            gameState[3] = 1; //36

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks 1st column


        else if (gameState[1] == gameState[4] && gameState[4] == 2 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.ximage);
            storeBoxNo.push(7);
            storeImageId.push(image8);
            gameState[7] = 1; //37

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[4] == gameState[7] && gameState[7] == 2 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.ximage);
            storeBoxNo.push(1);
            storeImageId.push(image2);
            gameState[1] = 1; //38

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[1] == gameState[7] && gameState[7] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 1; //39

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks 2nd column


        else if (gameState[2] == gameState[5] && gameState[5] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 1; //40

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[5] == gameState[8] && gameState[8] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 1; //41

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[2] == gameState[8] && gameState[8] == 2 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.ximage);
            storeBoxNo.push(5);
            storeImageId.push(image6);
            gameState[5] = 1; //42

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks 3rd column


        //Checks Diagonals -->

        else if (gameState[0] == gameState[4] && gameState[4] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.ximage);
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 1; //43

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[4] == gameState[8] && gameState[8] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.ximage);
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 1; //44

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[0] == gameState[8] && gameState[8] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 1; //45

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks Index (0,4,8) diagonal


        else if (gameState[2] == gameState[4] && gameState[4] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.ximage);
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 1; //46

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[4] == gameState[6] && gameState[6] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.ximage);
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 1; //47

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } else if (gameState[2] == gameState[6] && gameState[6] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.ximage);
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 1; //48

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        } // checks Index (2,4,6) diagonal


        // Otherwise AI chooses a random unoccupied position ------>
        else {
            Random random = new Random();
            int randomNumber;
            do {
                randomNumber = random.nextInt(9);
            } while (gameState[randomNumber] != 3);
            gameState[randomNumber] = 1;

            if (randomNumber == 0) {
                image1.setImageResource(R.drawable.ximage);
                storeBoxNo.push(0);
                storeImageId.push(image1);
            } else if (randomNumber == 1) {
                image2.setImageResource(R.drawable.ximage);
                storeBoxNo.push(1);
                storeImageId.push(image2);
            } else if (randomNumber == 2) {
                image3.setImageResource(R.drawable.ximage);
                storeBoxNo.push(2);
                storeImageId.push(image3);
            } else if (randomNumber == 3) {
                image4.setImageResource(R.drawable.ximage);
                storeBoxNo.push(3);
                storeImageId.push(image4);
            } else if (randomNumber == 4) {
                image5.setImageResource(R.drawable.ximage);
                storeBoxNo.push(4);
                storeImageId.push(image5);
            } else if (randomNumber == 5) {
                image6.setImageResource(R.drawable.ximage);
                storeBoxNo.push(5);
                storeImageId.push(image6);
            } else if (randomNumber == 6) {
                image7.setImageResource(R.drawable.ximage);
                storeBoxNo.push(6);
                storeImageId.push(image7);
            } else if (randomNumber == 7) {
                image8.setImageResource(R.drawable.ximage);
                storeBoxNo.push(7);
                storeImageId.push(image8);
            } else {
                image9.setImageResource(R.drawable.ximage);
                storeBoxNo.push(8);
                storeImageId.push(image9);
            }

            if(totalSelectedBoxes == 9) {
                WinDialogForAIasO resultDialog = new WinDialogForAIasO(PlayWithAIAsO.this, "Match Draw", PlayWithAIAsO.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        if (currentPlayerTurn == 2) {
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.black_box);
        } else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.black_box);
        }
    }

    private boolean checkResults(){
        boolean response = false;
        for (int i = 0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i);

            if (gameState[combination[0]] == turn && gameState[combination[1]] == turn &&
                    gameState[combination[2]] == turn) {
                response = true;
            }
        }
        return response;
    }

    public void restartMatch(){
        totalSelectedBoxes = 1;
        turn = 1;

        for(int i = 0; i < 9; i++) {
            gameState[i] = 3;
        }

        for(int i = 0; i < storeBoxNo.size(); i++) {
            storeBoxNo.pop();
        }

        for(int i = 0; i < storeImageId.size(); i++) {
            storeImageId.pop();
        }
        count = 0;

        image1.setImageResource(R.drawable.black_box);
        image2.setImageResource(R.drawable.black_box);
        image3.setImageResource(R.drawable.black_box);
        image4.setImageResource(R.drawable.black_box);
        image5.setImageResource(R.drawable.black_box);
        image6.setImageResource(R.drawable.black_box);
        image7.setImageResource(R.drawable.black_box);
        image8.setImageResource(R.drawable.black_box);
        image9.setImageResource(R.drawable.black_box);

        firstMove();
    }

    public void back() {
        Intent intent = new Intent(PlayWithAIAsO.this,ChoosePlayerMode.class);
        startActivity(intent);
        finish();
    }

    public int[] undo() {
        int[] values = new int[0];
        if (storeBoxNo.size() != 0 && storeImageId.size() != 0) {

            if (storeBoxNo.size() != 1 && storeImageId.size() != 1) {
                ++count;
                if (count < 3) {
                    gameState[storeBoxNo.peek()] = 3;
                    storeBoxNo.pop();
                    gameState[storeBoxNo.peek()] = 3;
                    storeBoxNo.pop();

                    storeImageId.peek().setImageResource(R.drawable.black_box);
                    storeImageId.pop();
                    storeImageId.peek().setImageResource(R.drawable.black_box);
                    storeImageId.pop();
                }
                else {
                    Toast.makeText(PlayWithAIAsO.this, "Maximum limit reached", Toast.LENGTH_SHORT).show();
                }
                values = new int[2];
                values[0] = 2;
                values[1] = totalSelectedBoxes - 2;
            }
            else {
                Toast.makeText(PlayWithAIAsO.this, "You can't undo AI's first move", Toast.LENGTH_SHORT).show();

                values = new int[2];
                values[0] = 2;
                values[1] = totalSelectedBoxes;
            }
        }
        return values;
    }

    public int[] firstMove() {
        for(int i = 0; i < 5000; i++) {}

        Random random = new Random();
        int randomNumber;
        do {
            randomNumber = random.nextInt(9);
        } while (gameState[randomNumber] != 3);
        gameState[randomNumber] = 1;

        if (randomNumber == 0) {
            image1.setImageResource(R.drawable.ximage);
            storeBoxNo.push(0);
            storeImageId.push(image1);
        } else if (randomNumber == 1) {
            image2.setImageResource(R.drawable.ximage);
            storeBoxNo.push(1);
            storeImageId.push(image2);
        } else if (randomNumber == 2) {
            image3.setImageResource(R.drawable.ximage);
            storeBoxNo.push(2);
            storeImageId.push(image3);
        } else if (randomNumber == 3) {
            image4.setImageResource(R.drawable.ximage);
            storeBoxNo.push(3);
            storeImageId.push(image4);
        } else if (randomNumber == 4) {
            image5.setImageResource(R.drawable.ximage);
            storeBoxNo.push(4);
            storeImageId.push(image5);
        } else if (randomNumber == 5) {
            image6.setImageResource(R.drawable.ximage);
            storeBoxNo.push(5);
            storeImageId.push(image6);
        } else if (randomNumber == 6) {
            image7.setImageResource(R.drawable.ximage);
            storeBoxNo.push(6);
            storeImageId.push(image7);
        } else if (randomNumber == 7) {
            image8.setImageResource(R.drawable.ximage);
            storeBoxNo.push(7);
            storeImageId.push(image8);
        } else {
            image9.setImageResource(R.drawable.ximage);
            storeBoxNo.push(8);
            storeImageId.push(image9);
        }
        turn = 2;
        changePlayerTurn(turn);

        int[] values = new int[2];
        values[0] = turn;
        values[1] = totalSelectedBoxes++;

        return values;
    }
}