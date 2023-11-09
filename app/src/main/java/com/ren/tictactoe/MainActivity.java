package com.ren.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ren.tictactoe.clases.Game;
import com.ren.tictactoe.clases.Player;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Game game;
    private Button[][] buttons;
    private TextView tv1;
    private boolean isAgainstAI = true;
    private ToggleButton tb1;
    private int row, col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game = new Game(new Player("", 'X'), new Player("", 'O'));

        buttons = new Button[3][3];
        buttons[0][0] = findViewById(R.id.button);
        buttons[0][1] = findViewById(R.id.button2);
        buttons[0][2] = findViewById(R.id.button3);
        buttons[1][0] = findViewById(R.id.button4);
        buttons[1][1] = findViewById(R.id.button5);
        buttons[1][2] = findViewById(R.id.button6);
        buttons[2][0] = findViewById(R.id.button7);
        buttons[2][1] = findViewById(R.id.button8);
        buttons[2][2] = findViewById(R.id.button9);

        tv1 = findViewById(R.id.tv1);
        tb1 = findViewById(R.id.tb1);
        tb1.setOnCheckedChangeListener((buttonView, isChecked) -> isAgainstAI = isChecked);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(true);
                buttons[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!game.isGameFinished()) {
            if (isAgainstAI) {
                int[] iaMovement = game.ia(game.getTable());
                game.performMovement(iaMovement[0], iaMovement[1], game.getPlayer2());
            } else {
                int clickedRow = -1;
                int clickedCol = -1;

                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (v == buttons[i][j]) {
                            clickedRow = i;
                            clickedCol = j;
                        }
                    }
                }
                if (clickedRow != -1 && clickedCol != -1) {
                    boolean validMove = game.performMovement(clickedRow, clickedCol, game.getPlayer1());
                    if (validMove) {
                        buttons[clickedRow][clickedCol].setText(String.valueOf(game.getPlayer1().getCharacter()));
                    }
                }
            }
            updateUI();
        }
    }

    @SuppressLint("SetTextI18n")
    private void updateUI() {
        if (game.isGameFinished()) {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    buttons[i][j].setEnabled(false);
                }
            }

            if (game.isWinner(game.getPlayer1())) {
                tv1.setText("Player 1 won");
            } else if (game.isWinner(game.getPlayer2())) {
                tv1.setText("Player 2 won");
            } else {
                tv1.setText("Tie");
            }
            tv1.setVisibility(View.VISIBLE);
        } else {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    char character = game.getTable()[i][j];
                    if (character != ' ') {
                        buttons[i][j].setText(String.valueOf(character));
                    }
                }
            }
        }
    }
}