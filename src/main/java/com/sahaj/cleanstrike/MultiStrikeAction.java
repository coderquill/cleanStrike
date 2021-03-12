package com.sahaj.cleanstrike;

public class MultiStrikeAction implements GameAction {
    @Override
    public Board executeOnBoard(Board board) {
        board.removeBlackCoins(2);
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.incrementScore(2);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return board.getBlackCoinCount() > 1;
    }
}
