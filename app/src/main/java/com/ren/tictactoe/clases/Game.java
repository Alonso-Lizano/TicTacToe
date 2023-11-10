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

    /**
     * Inicializa el tablero del juego estableciendo todas las celdas en blanco.
     */
    private void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = "";
            }
        }
    }

    /**
     * Realiza un movimiento en el tablero del juego.
     *
     * @param row La fila en la que se quiere realizar el movimiento.
     * @param column La columna en la que se quiere realizar el movimiento.
     * @return true si el movimiento es válido y se realizó correctamente, false si no es válido.
     */

    public boolean makeMovement(int row, int column) {
        if (row < 0 || row >= 3 || column < 0 || column >= 3 || !table[row][column].isEmpty()) {
            return false;
        }

        table[row][column] = currentPlayer.getCharacter();
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
        return true;
    }


    /**
     * Verifica si hay un ganador
     *
     * @return El caracter del ganador si hay un ganador, o una cadena vacía si no hay ganador ni empate.
     */
    public String verifyWinner() {
        // Verificar líneas horizontales y verticales
        for (int i = 0; i < 3; i++) {
            // Verificar línea horizontal en la fila i
            if (checkLine(table[i][0], table[i][1], table[i][2])) {
                return getWinner(table[i][0]);
            }
            // Verificar línea vertical en la columna i
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

    /**
     * Verifica si el juego ha terminado en empate.
     *
     * @return true si todas las celdas del tablero están ocupadas y no hay ganador, false lo contrario.
     */
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

    /**
     * Verifica si hay una línea ganadora
     *
     * @param cell1 Valor de la primera celda.
     * @param cell2 Valor de la segunda celda.
     * @param cell3 Valor de la tercera celda.
     * @return true si las celdas no están vacías y tienen el mismo valor, indicando una línea ganadora; false lo contrario.
     */
    private boolean checkLine(String cell1, String cell2, String cell3) {
        return !cell1.isEmpty() && cell1.equals(cell2) && cell2.equals(cell3);
    }

    /**
     * Obtiene el nombre del jugador ganador basado en su caracter.
     *
     * @param character El caracter del jugador ganador.
     * @return El nombre del jugador ganador.
     */
    private String getWinner(String character) {
        return (character.equals(player1.getCharacter())) ? player1.getName() : player2.getName();
    }

    /**
     * Obtiene el primer jugador del juego.
     *
     * @return El objeto Player que representa al primer jugador.
     */
    public Player getPlayer1() {
        return player1;
    }


    /**
     * Obtiene el segundo jugador del juego.
     *
     * @return El objeto Player que representa al segundo jugador.
     */
    public Player getPlayer2() {
        return player2;
    }


    /**
     * Obtiene la representación del tablero.
     *
     * @return Una array de cadenas que representa el tablero.
     */
    public String[][] getTable() {
        return table;
    }


}
