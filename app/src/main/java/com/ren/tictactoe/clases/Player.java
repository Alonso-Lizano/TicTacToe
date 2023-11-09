package com.ren.tictactoe.clases;

public class Player {

    private String name;
    private char character;

    public Player(String name, char character) {
        this.name = name;
        this.character = character;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }
}
