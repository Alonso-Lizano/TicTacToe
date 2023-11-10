package com.ren.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ren.tictactoe.clases.Game;
import com.ren.tictactoe.clases.Player;

public class MainActivity extends AppCompatActivity {

    private Game game;
    private Button[][] buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Player player1 = new Player("Player 1", "X");
        Player player2 = new Player("Player 2", "O");

        // Crear el juego
        game = new Game(player1, player2);

        configureButtons();
    }

    /**
     * Configura los botones del juego
     * Asigna los botones al array 'buttons', configura sus listeners y gestiona los clics.
     */
    private void configureButtons() {
        buttons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + (i * 3 + j + 1);
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);

                final int row = i;
                final int column = j;

                buttons[i][j].setOnClickListener(view -> {
                    // Obtener la fila y columna del botón clickeado
                    int rowClicked = row;
                    int columnClicked = column;

                    // Intentar hacer un movimiento en el juego
                    boolean validMovement = game.makeMovement(rowClicked, columnClicked);

                    if (validMovement) {
                        // Actualizar el tablero
                        updateUI(rowClicked, columnClicked);

                        // Verificar si hay un ganador
                        String winner = game.verifyWinner();
                        if (!winner.isEmpty()) {
                            showMessage("¡" + winner + " has won!");
                            resetGame();
                        } else if (game.isTie()) {
                            // Si el juego está empatado
                            showMessage("¡Tie!");
                            resetGame();
                        }
                    } else {
                        // Movimiento no válido (celda ocupada o fuera de los límites)
                        showMessage("Invalid movement. Try again.");
                    }
                });
            }
        }
    }

    /**
     * Actualiza la interfaz de usuario (UI) del juego al realizar un movimiento.
     *
     * @param row    La fila del movimiento.
     * @param column La columna del movimiento.
     */
    private void updateUI(int row, int column) {
        buttons[row][column].setText(game.getTable()[row][column]);
    }

    /**
     * Reinicia el juego, creando un nuevo objeto Game y reiniciando la UI.
     */
    private void resetGame() {
        game = new Game(game.getPlayer1(), game.getPlayer2());
        resetUI();
    }

    /**
     * Reinicia la UI, estableciendo el texto de todos los botones a cadena vacía.
     */
    private void resetUI() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
    }

    /**
     * Muestra un mensaje corto en forma de Toast.
     *
     * @param message El mensaje a mostrar.
     */
    private void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}