package com.sahaj.cleanstrike;


import java.util.HashMap;
import java.util.Map;



public class Board {
    private int redCoinCount;
    private int blackCoinCount;

    Board(int redCoinCount, int blackCoinCount){
       this.redCoinCount = redCoinCount;
       this.blackCoinCount = blackCoinCount;
    }

    public int getRedCoinCount(){
        return this.redCoinCount;
    }

    public int getBlackCoinCount(){
        return this.blackCoinCount;
    }

    public static Board buildDefaultBoard(){
        int defaultRedCoinCount = 1;
        int defaultBlackCoinCount = 9;

        return new Board(defaultRedCoinCount, defaultBlackCoinCount);
    }
}
