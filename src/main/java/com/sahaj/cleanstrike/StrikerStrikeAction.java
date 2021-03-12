package com.sahaj.cleanstrike;

public class StrikerStrikeAction implements GameAction {

    @Override
    public Board executeOnBoard(Board board) {
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.addCurrentActionResult(ActionType.FOUL);
        player.handleFoul();
        player.decrementScore(1);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return true;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {
        player.handleIfLastThreeActionsWereFoulty();
    }


}
