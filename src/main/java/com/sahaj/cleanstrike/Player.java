package com.sahaj.cleanstrike;

public class Player {

    private String name;
    private int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getPlayerName(){
        return this.name;
    }

    public int getPlayerScore(){
        return this.score;
    }

    public static Player initializePlayer(String name){
        int defaultScore = 0;
        return new Player(name, defaultScore);
    }

}
