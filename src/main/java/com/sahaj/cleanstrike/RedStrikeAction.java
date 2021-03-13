package com.sahaj.cleanstrike;

public class RedStrikeAction implements GameAction {
    @Override
    public Board executeOnBoard(Board board) {
        board.removeRedCoin();
        return board;
    }

    @Override
    public Player executeOnPlayer(Player player) {
        player.addCurrentActionResult(ActionType.NON_FOUL_ACTION);
        player.incrementScore(3);
        return player;
    }

    @Override
    public boolean canExecute(Board board, Player player) {
        return board.getRedCoinCount() > 0;
    }

    @Override
    public void handleIfLastThreeWereFaulty(Player player) {
//        player.handleIfLastThreeActionsWereFoulty();
    }
}
