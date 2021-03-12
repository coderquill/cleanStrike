package com.sahaj.cleanstrike;

public class Player {

    private String name;
    private int score;

    Player(String name, int score){
        this.name = name;
        this.score = score;
    }

    public String getName(){
        return this.name;
    }

    public int getScore(){
        return this.score;
    }

    public void incrementScore(int scoreToBeAdded){
        int currentScore = this.getScore();
        this.score = currentScore + scoreToBeAdded;
    }

    public void decrementScore(int scoreToBeSubtracted){
        int currentScore = this.getScore();
        this.score = currentScore - scoreToBeSubtracted;
    }

    public static Player initializePlayer(String name){
        int defaultScore = 0;
        return new Player(name, defaultScore);
    }

}
