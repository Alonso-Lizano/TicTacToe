package com.ren.tictactoe.clases;

import java.util.Random;

public class Game {

    private Player player1;
    private Player player2;
    private char[][] table;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.table = new char[3][3];
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public char[][] getTable() {
        return table;
    }

    public void setTable(char[][] table) {
        this.table = table;
    }

    public int[] ia(char[][] table) {
        Random random = new Random();
        int row;
        int column;
        do {
            row = random.nextInt(3);
            column = random.nextInt(3);
        } while (table[row][column] != ' ');

        return new int[]{row, column};
    }

    public boolean performMovement(int row, int column, Player player) {
        if (table[row][column] == ' ') {
            table[row][column] = player.getCharacter();
            if (isWinner(player)) {
                return true;
            } else if (isTie()) {
                return true;
            } else {
                int[] iaMovement = ia(table);
                table[iaMovement[0]][iaMovement[1]] = player2.getCharacter();
            }
            return true;
        }
        return false;
    }

    public boolean isGameFinished() {
        return isWinner(player1) || isWinner(player2) || isTie();
    }

    public boolean isWinner(Player player) {
        char character = player.getCharacter();

        // Verificar filas
        for (int i = 0; i < 3; i++) {
            if (table[i][0] == character && table[i][1] == character && table[i][2] == character) {
                return true;
            }
        }

        // Verificar columnas
        for (int j = 0; j < 3; j++) {
            if (table[0][j] == character && table[1][j] == character && table[2][j] == character) {
                return true;
            }
        }

        // Verificar diagonales
        if ((table[0][0] == character && table[1][1] == character && table[2][2] == character) ||
                (table[0][2] == character && table[1][1] == character && table[2][0] == character)) {
            return true;
        }

        return false;
    }

    private boolean isTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }


}
