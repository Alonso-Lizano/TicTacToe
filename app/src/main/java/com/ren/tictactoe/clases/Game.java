package com.ren.tictactoe.clases;

public class Game {

    private String[][] table;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.table = new String[3][3];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "";
            }
        }
    }

    public boolean makeMovement(int row, int column) {
        if (row < 0 || row >= 3 || column < 0 || column >= 3 || !table[row][column].isEmpty()) {
            return false;
        }

        table[row][column] = currentPlayer.getCharacter();
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        return true;
    }

    public String verifyWinner() {
        for (int i = 0; i < 3; i++) {
            if (checkLine(table[i][0], table[i][1], table[i][2])) {
                return getWinner(table[i][0]);
            }
            if (checkLine(table[0][i], table[1][i], table[2][i])) {
                return getWinner(table[0][i]);
            }
        }

        // Verificar diagonales
        if (checkLine(table[0][0], table[1][1], table[2][2])) {
            return getWinner(table[0][0]);
        }
        if (checkLine(table[0][2], table[1][1], table[2][0])) {
            return getWinner(table[0][2]);
        }
        return ""; // Si no hay ganador ni empate, retornar cadena vacía
    }


    public boolean isTie() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }


    private boolean checkLine(String cell1, String cell2, String cell3) {
        return !cell1.isEmpty() && cell1.equals(cell2) && cell2.equals(cell3);
    }

    private String getWinner(String character) {
        return (character.equals(player1.getCharacter())) ? player1.getName() : player2.getName();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String[][] getTable() {
        return table;
    }

}
