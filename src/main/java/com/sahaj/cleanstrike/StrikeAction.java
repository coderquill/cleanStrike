package com.sahaj.cleanstrike;

public class StrikeAction implements GameAction {

    @Override
    public Board executeOnBoard(Board board) {
        board.removeBlackCoins(1);
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.incrementScore(1);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return board.getBlackCoinCount() > 0;
    }
}

