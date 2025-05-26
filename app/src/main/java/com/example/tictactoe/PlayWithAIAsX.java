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

public class PlayWithAIAsX extends AppCompatActivity {

    private final List<int[]> combinationList = new ArrayList<>();
    private final Stack<Integer> storeBoxNo = new Stack<>();
    private final Stack<ImageView> storeImageId = new Stack<>();
    private Button Undo;
    private int turn = 1; //  0=finishGame, 1=Human'sTurn, 2=AI'sTurn
    private int totalSelectedBoxes = 1;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    ImageView back;
    int[] gameState = {3,3,3,3,3,3,3,3,3}; // 3=empty, 1=Human'sTurn, 2=AI'sTurn
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_with_ai_as_x);

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
    }

    public void Click(View view) {
        ImageView img = (ImageView) view;
        int tag1 = Integer.parseInt(view.getTag().toString());

        if(gameState[tag1] == 3 && turn == 1) {
            img.setImageResource(R.drawable.ximage);
            gameState[tag1] = 1;

            if (checkResults()) {
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Won the Match!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else if(totalSelectedBoxes == 9) {
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "Match Draw", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            } else {
                turn = 2;
                changePlayerTurn(turn);
                totalSelectedBoxes++;
                storeBoxNo.push(tag1);
                storeImageId.push(img);
            }
        }

        if(turn == 2) {
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

        if (gameState[0] == gameState[1] && gameState[1] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[2] = 2;
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
                //1
        } else if (gameState[1] == gameState[2] && gameState[2] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[0] = 2;
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
                //2
        } else if (gameState[0] == gameState[2] && gameState[2] == 2 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[1] = 2;//3
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
                //checks 1st row
        }


        else if (gameState[3] == gameState[4] && gameState[4] == 2 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[5] = 2; //4
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);resultDialog.show();
            return;
                //2
        } else if (gameState[4] == gameState[5] && gameState[5] == 2 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[3] = 2; //5
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
                //2
        } else if (gameState[3] == gameState[5] && gameState[5] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[4] = 2; //6
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
            //2
            } // checks 2nd row


        else if (gameState[6] == gameState[7] && gameState[7] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[8] = 2; //7
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
                //2
        } else if (gameState[7] == gameState[8] && gameState[8] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[6] = 2; //8
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[6] == gameState[8] && gameState[8] == 2 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[7] = 2; //9
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        }// checks 3rd row


        // Check Columns -->

        else if (gameState[0] == gameState[3] && gameState[3] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[6] = 2; //10
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[3] == gameState[6] && gameState[6] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[0] = 2; //11
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[0] == gameState[6] && gameState[6] == 2 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[3] = 2; //12
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } // checks 1st column


        else if (gameState[1] == gameState[4] && gameState[4] == 2 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[7] = 2; //13
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[4] == gameState[7] && gameState[7] == 2 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[1] = 2; //14
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[1] == gameState[7] && gameState[7] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[4] = 2; //15
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } // checks 2nd column


        else if (gameState[2] == gameState[5] && gameState[5] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[8] = 2; //16
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[5] == gameState[8] && gameState[8] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[2] = 2; //17
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[2] == gameState[8] && gameState[8] == 2 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[5] = 2; //18
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } // checks 3rd column


        //Checks Diagonals -->

        else if (gameState[0] == gameState[4] && gameState[4] == 2 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[8] = 2; //19
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[4] == gameState[8] && gameState[8] == 2 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[0] = 2; //20
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[0] == gameState[8] && gameState[8] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[4] = 2; //21

                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } // checks Index (0,4,8) diagonal


        else if (gameState[2] == gameState[4] && gameState[4] == 2 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[6] = 2; //22
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[4] == gameState[6] && gameState[6] == 2 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[2] = 2; //23
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } else if (gameState[2] == gameState[6] && gameState[6] == 2 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 0;
            gameState[4] = 2; //24
                WinDialogForAIasX resultDialog = new WinDialogForAIasX(PlayWithAIAsX.this, "You Lost The Game!", PlayWithAIAsX.this);
                resultDialog.setCancelable(false);
                resultDialog.show();
            return;
        } // checks Index (2,4,6) diagonal


        //AI checks for the winning conditions of the human ------>

        //Check Rows -->

        if (gameState[0] == gameState[1] && gameState[1] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 2; //25
                changePlayerTurn(1);
                totalSelectedBoxes++;
        } else if (gameState[1] == gameState[2] && gameState[2] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 2; //26
                changePlayerTurn(1);
                totalSelectedBoxes++; //2
        } else if (gameState[0] == gameState[2] && gameState[2] == 1 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(1);
            storeImageId.push(image2);
            gameState[1] = 2; //27
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } //checks 1st row


        else if (gameState[3] == gameState[4] && gameState[4] == 1 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(5);
            storeImageId.push(image6);
            gameState[5] = 2; //28
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[4] == gameState[5] && gameState[5] == 1 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(3);
            storeImageId.push(image4);
            gameState[3] = 2; //29
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[3] == gameState[5] && gameState[5] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 2; //30
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks 2nd row


        else if (gameState[6] == gameState[7] && gameState[7] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 2; //31
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[7] == gameState[8] && gameState[8] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 2; //32
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[6] == gameState[8] && gameState[8] == 1 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(7);
            storeImageId.push(image8);
            gameState[7] = 2; //33
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks 3rd row


        // Check Columns -->

        else if (gameState[0] == gameState[3] && gameState[3] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 2; //34
                changePlayerTurn(1);
                totalSelectedBoxes++;//2

        } else if (gameState[3] == gameState[6] && gameState[6] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 2; //35

                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[0] == gameState[6] && gameState[6] == 1 && gameState[3] == 3) {
            image4.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(3);
            storeImageId.push(image4);
            gameState[3] = 2; //36
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks 1st column


        else if (gameState[1] == gameState[4] && gameState[4] == 1 && gameState[7] == 3) {
            image8.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(7);
            storeImageId.push(image8);
            gameState[7] = 2; //37
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[4] == gameState[7] && gameState[7] == 1 && gameState[1] == 3) {
            image2.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(1);
            storeImageId.push(image2);
            gameState[1] = 2; //38
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[1] == gameState[7] && gameState[7] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 2; //39
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks 2nd column


        else if (gameState[2] == gameState[5] && gameState[5] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 2; //40
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[5] == gameState[8] && gameState[8] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 2; //41
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[2] == gameState[8] && gameState[8] == 1 && gameState[5] == 3) {
            image6.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(5);
            storeImageId.push(image6);
            gameState[5] = 2; //42
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks 3rd column


        //Checks Diagonals -->

        else if (gameState[0] == gameState[4] && gameState[4] == 1 && gameState[8] == 3) {
            image9.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(8);
            storeImageId.push(image9);
            gameState[8] = 2; //43
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[4] == gameState[8] && gameState[8] == 1 && gameState[0] == 3) {
            image1.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(0);
            storeImageId.push(image1);
            gameState[0] = 2; //44
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[0] == gameState[8] && gameState[8] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 2; //45
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks Index (0,4,8) diagonal


        else if (gameState[2] == gameState[4] && gameState[4] == 1 && gameState[6] == 3) {
            image7.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(6);
            storeImageId.push(image7);
            gameState[6] = 2; //46
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[4] == gameState[6] && gameState[6] == 1 && gameState[2] == 3) {
            image3.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(2);
            storeImageId.push(image3);
            gameState[2] = 2; //47
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } else if (gameState[2] == gameState[6] && gameState[6] == 1 && gameState[4] == 3) {
            image5.setImageResource(R.drawable.oimage);
            turn = 1;
            storeBoxNo.push(4);
            storeImageId.push(image5);
            gameState[4] = 2; //48
                changePlayerTurn(1);
                totalSelectedBoxes++;//2
        } // checks Index (2,4,6) diagonal


        // Otherwise AI chooses a random unoccupied position ------>
        else {
            Random random = new Random();
            int randomNumber;
            do {
                randomNumber = random.nextInt(9);
            } while (gameState[randomNumber] != 3);
            gameState[randomNumber] = 2;

            if (randomNumber == 0) {
                image1.setImageResource(R.drawable.oimage);
                storeBoxNo.push(0);
                storeImageId.push(image1);
            } else if (randomNumber == 1) {
                image2.setImageResource(R.drawable.oimage);
                storeBoxNo.push(1);
                storeImageId.push(image2);
            } else if (randomNumber == 2) {
                image3.setImageResource(R.drawable.oimage);
                storeBoxNo.push(2);
                storeImageId.push(image3);
            } else if (randomNumber == 3) {
                image4.setImageResource(R.drawable.oimage);
                storeBoxNo.push(3);
                storeImageId.push(image4);
            } else if (randomNumber == 4) {
                image5.setImageResource(R.drawable.oimage);
                storeBoxNo.push(4);
                storeImageId.push(image5);
            } else if (randomNumber == 5) {
                image6.setImageResource(R.drawable.oimage);
                storeBoxNo.push(5);
                storeImageId.push(image6);
            } else if (randomNumber == 6) {
                image7.setImageResource(R.drawable.oimage);
                storeBoxNo.push(6);
                storeImageId.push(image7);
            } else if (randomNumber == 7) {
                image8.setImageResource(R.drawable.oimage);
                storeBoxNo.push(7);
                storeImageId.push(image8);
            } else {
                image9.setImageResource(R.drawable.oimage);
                storeBoxNo.push(8);
                storeImageId.push(image9);
            }
            turn = 1;
                changePlayerTurn(turn);
                totalSelectedBoxes++;//2
        }
    }

    private void changePlayerTurn(int currentPlayerTurn) {
        if (currentPlayerTurn == 1) {
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
    }

    public void back() {
            Intent intent = new Intent(PlayWithAIAsX.this,ChoosePlayerMode.class);
            startActivity(intent);
            finish();
    }

    public int[] undo() {
        if(storeBoxNo.size() != 0 && storeImageId.size() != 0) {
            ++count;
            if(count < 3) {
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
                Toast.makeText(PlayWithAIAsX.this, "Maximum limit reached", Toast.LENGTH_SHORT).show();
            }
        }
        int[] values = new int[2];
        values[0] = 1;
        values[1] = totalSelectedBoxes - 2 ;

        return values;
    }
}