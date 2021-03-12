package com.sahaj.cleanstrike;


public class Board {
    private int redCoinCount;
    private int blackCoinCount;

    private Board(int redCoinCount, int blackCoinCount) {
        this.redCoinCount = redCoinCount;
        this.blackCoinCount = blackCoinCount;
    }

    public int getRedCoinCount() {
        return this.redCoinCount;
    }

    public int getBlackCoinCount() {
        return this.blackCoinCount;
    }

    public int getTotalCoinCount() {
        return this.blackCoinCount + this.redCoinCount;
    }

    public void removeBlackCoins(int coinsToRemove) {
        this.blackCoinCount = this.blackCoinCount - coinsToRemove;
    }

    public void removeRedCoin() {
        int currentRedCoinCount = this.getRedCoinCount();
        if (currentRedCoinCount == 0) {
            System.out.println("There is no red coin present in the game");
        } else {
            this.redCoinCount = currentRedCoinCount - 1;
        }
    }

    public static Board buildBoard(int redCoinCount, int blackCoinCount) throws InvalidBoardException {
        if (redCoinCount < 0 || blackCoinCount < 0) {
            throw new InvalidBoardException("Coin Count cannot be negative");
        }
        return new Board(redCoinCount, blackCoinCount);
    }

    public static Board buildDefaultBoard() throws InvalidBoardException {
        int defaultRedCoinCount = 1;
        int defaultBlackCoinCount = 9;

        return buildBoard(defaultRedCoinCount, defaultBlackCoinCount);
    }

    public boolean isGameFinished(){
        return this.getTotalCoinCount() == 0;
    }


}
