package com.sahaj.cleanstrike;

public class StrikerStrikeAction implements GameAction {

    @Override
    public Board executeOnBoard(Board board) {
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.decrementScore(1);
        player.handleFoul();
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return true;
    }

}
