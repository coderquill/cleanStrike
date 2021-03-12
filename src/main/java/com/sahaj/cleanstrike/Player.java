package com.sahaj.cleanstrike;

public class Player {

    private String name;
    private int score;
    private int foulCount;

    Player(String name, int score) {
        this.name = name;
        this.score = score;
        this.foulCount = 0;
    }

    public String getName() {
        return this.name;
    }

    public int getScore() {
        return this.score;
    }

    public void incrementScore(int scoreToBeAdded) {
        int currentScore = this.getScore();
        this.score = currentScore + scoreToBeAdded;
    }

    public void decrementScore(int scoreToBeSubtracted) {
        int currentScore = this.getScore();
        this.score = currentScore - scoreToBeSubtracted;
    }

    public int getFoulCount() {
        return this.foulCount;
    }

    public void handleFoul() {
        int currentFoulCount = this.getFoulCount();
        if(currentFoulCount == 2){
            resetFoulCount();
            decrementScore(1);
        }else if( currentFoulCount < 3){
            addFoul();
        }
    }

    public void resetFoulCount(){
        this.foulCount = 0;
    }


    public void addFoul(){
        this.foulCount = this.getFoulCount()+1;
    }

    public static Player initializePlayer(String name) {
        int defaultScore = 0;
        return new Player(name, defaultScore);
    }

}
